package com.example.islam.trainingcenter.app;

import android.app.Application;
import android.widget.Toast;

import com.example.islam.trainingcenter.Base.BaseApplication;

import java.text.SimpleDateFormat;
import java.util.Locale;


public class MainApplication extends BaseApplication {



    public static Application sAppContext;

    public static SimpleDateFormat simpleDateFormat= new SimpleDateFormat("dd-MM-yyyy",new Locale("en"));

    @Override
    public void onCreate() {
        super.onCreate();
        sAppContext = this;

    }

    public static void toastMsg(String msg){
        Toast.makeText(sAppContext,msg, Toast.LENGTH_LONG).show();
    }




}
