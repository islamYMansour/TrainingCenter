package com.example.islam.trainingcenter.Base;

import android.support.v4.app.Fragment;
import android.content.Context;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.IgnoreWhen;

/**
 * Created by islam on 11/7/2017.
 */

public class BaseFragment extends Fragment {

    private BaseActivity mActivity;

    @Override
    public void onAttach(Context context){
        super.onAttach(context);
        mActivity= (BaseActivity) context;

    }


    @Override
    public void onDetach(){
        super.onDetach();
        mActivity=null;

    }



    @IgnoreWhen(IgnoreWhen.State.VIEW_DESTROYED)
    protected void postView(){

    }

    @AfterViews
    protected void afterViews(){
        postView();

    }


    protected void showLoading(){
        showLoading("");
    }

    protected void showLoading(String withMsg){
        if(mActivity!=null){
            mActivity.showLoading();
        }
    }

    public void hideLoading(){
        if(mActivity!=null){
            mActivity.hideLoading();
        }
    }





}
