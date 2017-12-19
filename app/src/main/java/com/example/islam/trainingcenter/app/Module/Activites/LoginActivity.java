package com.example.islam.trainingcenter.app.Module.Activites;

import android.app.AlertDialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.islam.trainingcenter.Base.BaseActivity;
import com.example.islam.trainingcenter.DB.DBFacade;
import com.example.islam.trainingcenter.DB.SQLiteHelper;
import com.example.islam.trainingcenter.R;
import com.example.islam.trainingcenter.Utilites.CheckUtil;
import com.example.islam.trainingcenter.Utilites.Utils;
import com.example.islam.trainingcenter.app.Module.FragmentHolderActivity;


/**
 * Created by islam on 11/7/2017.
 */

public class LoginActivity extends BaseActivity {


    private DBFacade facade;
    private AlertDialog.Builder dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        final EditText phone = (EditText) findViewById(R.id.phone);
        final EditText password = (EditText) findViewById(R.id.password);
        Button register = (Button) findViewById(R.id.register);
        Button login = (Button) findViewById(R.id.login);
        dialog=new AlertDialog.Builder(LoginActivity.this);

        facade = new DBFacade(this);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                facade.open();
                if (!TextUtils.isEmpty(phone.getText()) && !TextUtils.isEmpty(password.getText())) {
                    if (CheckUtil.checkPhone(phone.getText()) && CheckUtil.checkPass(password.getText())) {
                        Cursor cursor = facade.getAminByCoulmn(SQLiteHelper.ADMIN_PHONE_COULMN, SQLiteHelper.ADMIN_TABLE,phone.getText().toString());
                        if (cursor.getCount() <= 0) {

                            dialog.setMessage(R.string.not_valid).setTitle(R.string.wrong).setIcon(R.drawable.cancel).show();
                        } else {
                            cursor.moveToFirst();
                            String commingPass = Utils.encrypt(password.getText().toString().trim());
                            String DataBasePass = cursor.getString(cursor.getColumnIndex(SQLiteHelper.ADMIN_PASSWORD_COULMN));
                            if (commingPass.equals(DataBasePass)) {
                                Utils.saveUser(LoginActivity.this,phone.getText().toString());
                               Intent intent = new Intent(LoginActivity.this, FragmentHolderActivity.class);
                                startActivity(intent);
                                finish();
                            }
                            else{
                                dialog.setMessage(R.string.not_valid).setTitle(R.string.wrong).setIcon(R.drawable.cancel).show();

                            }
                        }
                    } else {
                        phone.setError(getString(R.string.validate_phone));
                    }
                } else {
                    password.setError(getString(R.string.complete_field));
                    phone.setError(getString(R.string.complete_field));
                }
                facade.close();
            }
        });


        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));

            }
        });


    }

}
