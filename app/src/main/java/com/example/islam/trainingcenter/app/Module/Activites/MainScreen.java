package com.example.islam.trainingcenter.app.Module.Activites;


import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.daimajia.slider.library.Tricks.ViewPagerEx;
import com.example.islam.trainingcenter.Base.BaseActivity;
import com.example.islam.trainingcenter.Base.BaseFragment;
import com.example.islam.trainingcenter.DB.DBFacade;
import com.example.islam.trainingcenter.DB.SQLiteHelper;
import com.example.islam.trainingcenter.DB.Tables.TCourse;
import com.example.islam.trainingcenter.R;
import com.example.islam.trainingcenter.Utilites.Utils;
import com.example.islam.trainingcenter.app.Module.Adapters.AdsAdapter;
import com.example.islam.trainingcenter.app.Module.Adapters.TrianeeCourseAdapter;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by islam on 12/14/2017.
 */

public class MainScreen extends BaseFragment implements BaseSliderView.OnSliderClickListener, ViewPagerEx.OnPageChangeListener {
    private SliderLayout mDemoSlider;
    private RecyclerView recyclerView;
    private List<TCourse>courses;
    private TrianeeCourseAdapter trianeeCourseAdapter;
    private DBFacade dbFacade;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_main, container, false);
        dbFacade = new DBFacade(getContext());

        recyclerView = view.findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        courses = new ArrayList<>();
        trianeeCourseAdapter= new TrianeeCourseAdapter(getContext(),courses);
        recyclerView.setAdapter(trianeeCourseAdapter);

        getCourses();



        mDemoSlider = view.findViewById(R.id.slider);
        final HashMap<String, Integer> file_maps = new HashMap<String, Integer>();
        file_maps.put("Center", R.drawable.background);
        file_maps.put("course", R.drawable.course_img);
        file_maps.put("IT course", R.drawable.background);
        file_maps.put("ENG course", R.drawable.logo);

        for (String name : file_maps.keySet()) {
            TextSliderView textSliderView = new TextSliderView(getContext());
            // initialize a SliderLayout
            textSliderView
                    .description(name)
                    .image(file_maps.get(name))
                    .setScaleType(BaseSliderView.ScaleType.Fit)
                    .setOnSliderClickListener(this);

            //add your extra information
            textSliderView.bundle(new Bundle());
            textSliderView.getBundle()
                    .putString("extra", name);

            mDemoSlider.addSlider(textSliderView);
        }
        mDemoSlider.setPresetTransformer(SliderLayout.Transformer.Accordion);
        mDemoSlider.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
        mDemoSlider.setCustomAnimation(new DescriptionAnimation());
        mDemoSlider.setDuration(4000);
        return view;
    }

    @Override
    public void onSliderClick(BaseSliderView slider) {

    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }



    void getCourses() {
        dbFacade.open();
        Cursor cursor = dbFacade.getAllCourses();
        courses.clear();
        while (cursor.moveToNext()) {
            int pk = Integer.valueOf(cursor.getString(cursor.getColumnIndex(SQLiteHelper.PK_ID_COULMN)));

            TCourse course = new TCourse.CourseBuilder()
                    .setPkID(pk)
                    .setCourseTrainer(cursor.getString(cursor.getColumnIndex(SQLiteHelper.COURSE_COURSETRAINER_COLUMN)))
                    .setDate(cursor.getString(cursor.getColumnIndex(SQLiteHelper.COURSE_DATE_COULMN)))
                    .setForignKey(cursor.getString(cursor.getColumnIndex(SQLiteHelper.FORIGN_KEY_COULMN)))
                    .setHoursCount(cursor.getString(cursor.getColumnIndex(SQLiteHelper.COURSE_HOURS_COLUMN)))
                    .setName(cursor.getString(cursor.getColumnIndex(SQLiteHelper.COURSE_NAME_COLUMN)))
                    .setPlace(cursor.getString(cursor.getColumnIndex(SQLiteHelper.COURSE_PLACE_COLUMN)))
                    .setPrice(cursor.getString(cursor.getColumnIndex(SQLiteHelper.COURSE_PRICE_COLUMN))).build();

            courses.add(course);
        }
        trianeeCourseAdapter.notifyDataSetChanged();

        dbFacade.close();

    }

}
