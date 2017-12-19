package com.example.islam.trainingcenter.Base;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import org.androidannotations.annotations.AfterViews;

/**
 * Created by islam on 11/7/2017.
 */

public class BaseActivity extends AppCompatActivity{



    ProgressDialog mLoading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initLoading();
        preView();

    }



    private void initLoading() {
        mLoading = new ProgressDialog(this);
        mLoading.setCancelable(false);
        mLoading.setTitle("");
        mLoading.setMessage("");
    }




    public void showLoading() {
        showLoading("");

    }

    public void showLoading(String withMsg) {
        mLoading.setMessage(withMsg);
        if (mLoading != null && !mLoading.isShowing()) {
            mLoading.show();
        }
    }

    public void hideLoading() {
        if (mLoading != null && mLoading.isShowing()) {
            mLoading.dismiss();
        }
    }


    protected void preView() {

    }

    @Override
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
    }


    @Override
    protected void onDestroy() {
        hideLoading();
        super.onDestroy();
    }



    @AfterViews
    protected void afterViews() {
        postView();
    }


    protected void postView() {
    }



}
