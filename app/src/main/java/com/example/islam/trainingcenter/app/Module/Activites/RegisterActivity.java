package com.example.islam.trainingcenter.app.Module.Activites;

import android.app.AlertDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.islam.trainingcenter.Base.BaseActivity;
import com.example.islam.trainingcenter.DB.DBFacade;
import com.example.islam.trainingcenter.DB.SQLiteHelper;
import com.example.islam.trainingcenter.DB.Tables.TAdmin;
import com.example.islam.trainingcenter.R;
import com.example.islam.trainingcenter.Utilites.CheckUtil;
import com.example.islam.trainingcenter.Utilites.Utils;
import com.facebook.drawee.view.SimpleDraweeView;

/**
 * Created by islam on 11/7/2017.
 */

public class RegisterActivity extends BaseActivity {

    private static final int PICK_IMAGE = 1;
    private static final String SELETED_IMAGE = "selected image";
    private SimpleDraweeView profilePic;
    private DBFacade db;
    private AlertDialog.Builder dialog;
    private long id;

    private Uri selectedImageUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        final EditText userName = (EditText) findViewById(R.id.user_name);
        final EditText password = (EditText) findViewById(R.id.password);
        final EditText confirmPassword = (EditText) findViewById(R.id.confirm_password);
        final EditText emailText = (EditText) findViewById(R.id.email);
        final EditText phone = (EditText) findViewById(R.id.phone);
        final Button registerBtn = (Button) findViewById(R.id.register);

        profilePic = (SimpleDraweeView) findViewById(R.id.logo);
        db = new DBFacade(this);
        dialog = new AlertDialog.Builder(this);


        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!TextUtils.isEmpty(userName.getText()) && !TextUtils.isEmpty(password.getText()) && !TextUtils.isEmpty(confirmPassword.getText()) && !TextUtils.isEmpty(phone.getText()) && !TextUtils.isEmpty(emailText.getText())) {
                    if (CheckUtil.checkEmail(emailText.getText())) {
                        if (CheckUtil.checkPass(password.getText())) {
                            if (password.getText().toString().equals(confirmPassword.getText().toString())) {
                                if (CheckUtil.checkPhone(phone.getText())) {
                                    if (!db.isExist(SQLiteHelper.ADMIN_TABLE, SQLiteHelper.ADMIN_PHONE_COULMN, phone.getText().toString())) {
                                        showLoading();
                                        new Handler().postDelayed(new Runnable() {
                                            @Override
                                            public void run() {
                                                db.open();
                                                TAdmin admin = new TAdmin.AdminBuilder()
                                                        .setPassword(Utils.encrypt(password.getText().toString()))
                                                        .setEmail(emailText.getText().toString())
                                                        .setImagePath(selectedImageUri + "")
                                                        .setName(userName.getText().toString())
                                                        .setPhone(phone.getText().toString())
                                                        .build();
                                                id = db.inserAdmin(admin);
                                                if (id <= 0)
                                                    dialog.setIcon(R.drawable.cancel).setMessage(R.string.register_not_complete).setTitle(R.string.wrong).setNegativeButton(R.string.close, null).show();
                                                else {
                                                    dialog.setIcon(R.drawable.successful).setMessage(R.string.register_complete).setTitle(R.string.complete).setNegativeButton(R.string.close, null).show();

                                                }
                                                hideLoading();
                                                db.close();
                                            }
                                        }, 3000);
                                    } else
                                        dialog.setIcon(R.drawable.cancel).setMessage(R.string.account_exist).setTitle(R.string.wrong).setNegativeButton(R.string.close, null).show();

                                } else {
                                    phone.setError(getString(R.string.phone_not_valid));
                                }

                            } else {
                                confirmPassword.setError(getString(R.string.password_not_same));
                            }
                        } else
                            password.setError(getString(R.string.password_min));

                    } else {
                        emailText.setError(getString(R.string.email_not_valid));
                    }
                } else {
                    phone.setError(getString(R.string.complete_field));
                    emailText.setError(getString(R.string.complete_field));
                    password.setError(getString(R.string.complete_field));
                    confirmPassword.setError(getString(R.string.complete_field));
                    userName.setError(getString(R.string.complete_field));

                }


            }
        });


        profilePic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, SELETED_IMAGE), PICK_IMAGE);


            }
        });


    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == PICK_IMAGE) {
            selectedImageUri = data.getData();
            // selectedImagePath = Utils.getPath(this,selectedImageUri);
            profilePic.setImageURI(selectedImageUri);


        }
    }

}
