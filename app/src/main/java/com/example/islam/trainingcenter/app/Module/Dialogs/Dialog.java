package com.example.islam.trainingcenter.app.Module.Dialogs;

import android.app.AlertDialog;
import android.content.Context;

/**
 * Created by islam on 11/8/2017.
 */

public class Dialog {

    private static Context context;

    private static AlertDialog.Builder sInstance;

    private Dialog(Context context) {
        this.context = context;
    }

    public static   AlertDialog.Builder getInstance (){
        if(sInstance==null)
            sInstance = new AlertDialog.Builder(context, android.support.design.R.style.AlertDialog_AppCompat);
        return sInstance;
    }


}
