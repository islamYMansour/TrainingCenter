package com.example.islam.trainingcenter.app.Module.Activites;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Color;
import android.media.Image;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ImageButton;

import com.example.islam.trainingcenter.Base.BaseFragment;
import com.example.islam.trainingcenter.DB.CoursesDB;
import com.example.islam.trainingcenter.DB.DB;
import com.example.islam.trainingcenter.DB.DBFacade;
import com.example.islam.trainingcenter.DB.SQLiteHelper;
import com.example.islam.trainingcenter.DB.Tables.TAds;
import com.example.islam.trainingcenter.DB.Tables.TCenter;
import com.example.islam.trainingcenter.DB.Tables.TCourse;
import com.example.islam.trainingcenter.R;
import com.example.islam.trainingcenter.Utilites.FragmentUtil;
import com.example.islam.trainingcenter.Utilites.Utils;
import com.example.islam.trainingcenter.app.Module.Adapters.AdsAdapter;
import com.example.islam.trainingcenter.app.Module.Adapters.CourseAdapter;
import com.yanzhenjie.recyclerview.swipe.SwipeItemClickListener;
import com.yanzhenjie.recyclerview.swipe.SwipeMenu;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuBridge;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuCreator;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuItem;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuItemClickListener;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by islam on 12/16/2017.
 */

public class ManageAds extends BaseFragment {

    public static final String PK_KEY = "primary";
    private RecyclerView mRecyclerView;
    private List<TAds> ads;
    private DBFacade db;
    private AdsAdapter adsAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.actvity_ads_list, container, false);
        ImageButton add = view.findViewById(R.id.add_course);
        mRecyclerView = view.findViewById(R.id.recycler);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        db = new DBFacade(getContext());
        ads = new ArrayList<>();
        adsAdapter = new AdsAdapter(getContext(), ads);
        mRecyclerView.setAdapter(adsAdapter);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentUtil.replaceFragment(getContext(), new AddAd(), R.id.container);
            }
        });

getAllAds();
        return view;
    }

    void getAllAds() {

        db.open();
        Cursor cursor = db.getAllAds();
        while (cursor.moveToNext()) {
            TAds aAds = new TAds.AdsBuilder()
                    .setImagePath(cursor.getBlob(cursor.getColumnIndex(SQLiteHelper.ADS_IMAGE_COULMN)))
                    .setTitle(cursor.getString(cursor.getColumnIndex(SQLiteHelper.ADS_NAME_COLUMN))).build();
            ads.add(aAds);
        }

        adsAdapter.notifyDataSetChanged();

        db.close();


    }


}
