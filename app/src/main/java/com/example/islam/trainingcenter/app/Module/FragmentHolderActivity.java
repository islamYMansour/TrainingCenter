package com.example.islam.trainingcenter.app.Module;


import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AlertDialog;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.islam.trainingcenter.Base.BaseActivity;
import com.example.islam.trainingcenter.DB.DBFacade;
import com.example.islam.trainingcenter.DB.SQLiteHelper;
import com.example.islam.trainingcenter.R;
import com.example.islam.trainingcenter.Utilites.FragmentUtil;
import com.example.islam.trainingcenter.Utilites.Utils;
import com.example.islam.trainingcenter.app.Module.Activites.LoginActivity;
import com.example.islam.trainingcenter.app.Module.Activites.MainScreen;
import com.example.islam.trainingcenter.app.Module.Activites.ManageAds;
import com.example.islam.trainingcenter.app.Module.Activites.ManageCourseActivity;
import com.facebook.drawee.view.SimpleDraweeView;

/**
 * Created by islam on 12/1/2017.
 */

public class FragmentHolderActivity extends BaseActivity {

    AlertDialog.Builder dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);

        dialog = new AlertDialog.Builder(this);
        Fragment fragment = new MainScreen();
        FragmentUtil.addFragmentWithBackStack(this, fragment, R.id.container);

        final DrawerLayout drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        ImageButton openNavigate = (ImageButton) findViewById(R.id.open_navigation);
        Button logout = (Button) findViewById(R.id.logout);


        openNavigate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.openDrawer(GravityCompat.END);

            }
        });


        View header = navigationView.getHeaderView(0);
        SimpleDraweeView userPic = header.findViewById(R.id.user_pic);
        TextView userName = header.findViewById(R.id.user_name);

        String phone = Utils.getUser(this);
        DBFacade db = new DBFacade(this);
        db.open();
        final Cursor cursor = db.getAminByCoulmn(SQLiteHelper.ADMIN_PHONE_COULMN, SQLiteHelper.ADMIN_TABLE, phone);
        cursor.moveToFirst();
        String nameString = cursor.getString(cursor.getColumnIndex(SQLiteHelper.ADMIN_NAME_COLUMN));
        String ImagePath = cursor.getString(cursor.getColumnIndex(SQLiteHelper.ADMIN_IMAGE_PATH));
        userPic.setImageURI(Uri.parse(ImagePath));
        userName.setText(nameString);
        db.close();


        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            Fragment fragment=new MainScreen() ;
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.manage_ads:
                        fragment= new ManageAds();

                        break;
                    case R.id.manage_courses:
                        fragment= new ManageCourseActivity();

                        break;
                    case R.id.call_us:
                        break;
                    case R.id.about_app:
                        break;
                }

                FragmentUtil.replaceFragment(FragmentHolderActivity.this,fragment,R.id.container);
                drawerLayout.closeDrawer(GravityCompat.END);


                return true;
            }
        });


        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dialog.setIcon(R.drawable.cancel)
                        .setTitle(R.string.logout)
                        .setMessage(R.string.logout_assure)
                        .setNegativeButton(R.string.cancel, null)
                        .setPositiveButton(R.string.yes_logout, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Utils.removeUser(FragmentHolderActivity.this);
                                startActivity(new Intent(FragmentHolderActivity.this, LoginActivity.class));
                                finish();
                            }
                        }).show();

            }
        });
    }


}
