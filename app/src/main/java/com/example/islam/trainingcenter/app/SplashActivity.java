package com.example.islam.trainingcenter.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.islam.trainingcenter.R;
import com.example.islam.trainingcenter.Utilites.Utils;
import com.example.islam.trainingcenter.app.Module.Activites.LoginActivity;
import com.example.islam.trainingcenter.app.Module.Activites.MainScreen;
import com.example.islam.trainingcenter.app.Module.FragmentHolderActivity;

public class SplashActivity extends Activity {

    private static final long SPLASH_TIME_OUT = 1500L;
    private static final int BACK_GROUND_RESOURCE_ID = R.drawable.splash;//R.mipmap.splash;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        Log.i("LifeCycle", "onCreate");

        ImageView img = new ImageView(getBaseContext());
        img.setScaleType(ImageView.ScaleType.FIT_XY);
        try {
            Log.i("LifeCycle", "start setImageResource");
            img.setImageResource(BACK_GROUND_RESOURCE_ID);
            Log.i("LifeCycle", "end setImageResource");

        } catch (OutOfMemoryError exc) {
            Toast.makeText(this, "لايوجد مساحة كافية في ذاكرة الجهاز,قم باغلاق بعض البرامج", Toast.LENGTH_LONG).show();
        }

        setContentView(img, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                if (Utils.getUser(SplashActivity.this).trim().isEmpty())
                    startActivity(new Intent(SplashActivity.this, LoginActivity.class));
                else
            startActivity(new Intent(SplashActivity.this, FragmentHolderActivity.class));

                finish();
            }
        }, SPLASH_TIME_OUT);

    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("LifeCycle", "onResume");

    }
}
