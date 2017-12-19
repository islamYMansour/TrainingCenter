package com.example.islam.trainingcenter.Base;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import com.facebook.drawee.backends.pipeline.Fresco;


/**
 * Created by islam on 11/7/2017.
 */

public class BaseApplication extends Application {


    public static Context sContext;



    @Override
    public void onCreate() {
        super.onCreate();
        sContext = getApplicationContext();
        //PercentViewHelper.initRefference(this);
        long s = System.currentTimeMillis();
        initHelpers();
        Log.i("LifeCycle", "initHelpers takes" + (System.currentTimeMillis() - s));

        Fresco.initialize(this);
    }


    protected void initHelpers() {

//
    }
}
