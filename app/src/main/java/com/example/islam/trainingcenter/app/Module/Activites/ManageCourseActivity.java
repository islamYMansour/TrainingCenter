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
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.example.islam.trainingcenter.Base.BaseFragment;
import com.example.islam.trainingcenter.DB.CoursesDB;
import com.example.islam.trainingcenter.DB.DB;
import com.example.islam.trainingcenter.DB.DBFacade;
import com.example.islam.trainingcenter.DB.SQLiteHelper;
import com.example.islam.trainingcenter.DB.Tables.TCourse;
import com.example.islam.trainingcenter.R;
import com.example.islam.trainingcenter.Utilites.FragmentUtil;
import com.example.islam.trainingcenter.Utilites.Utils;
import com.example.islam.trainingcenter.app.Module.Adapters.CourseAdapter;
import com.yanzhenjie.recyclerview.swipe.SwipeItemClickListener;
import com.yanzhenjie.recyclerview.swipe.SwipeMenu;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuBridge;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuCreator;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuItem;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuItemClickListener;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;

import java.util.ArrayList;

/**
 * Created by islam on 12/16/2017.
 */

public class ManageCourseActivity extends BaseFragment {


    public static final String PK_KEY ="primary" ;
    SwipeMenuRecyclerView mRecyclerView;
    ArrayList<TCourse> courses;
    CourseAdapter adapter;
    DBFacade dbFacade;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_manage_courses, container, false);

        ImageButton add = view.findViewById(R.id.add_course);

        dbFacade= new DBFacade(getContext());
        mRecyclerView = view.findViewById(R.id.recycler);
        mRecyclerView.setLayoutManager(getLayoutManager());
        mRecyclerView.setSwipeMenuCreator(mSwipeMenuCreator);

        mRecyclerView.setSwipeItemClickListener(mItemClickListener);
        mRecyclerView.setSwipeMenuItemClickListener(mMenuItemClickListener);
        mRecyclerView.setLongPressDragEnabled(true);
        mRecyclerView.setItemViewSwipeEnabled(false);



        courses = new ArrayList<>();
        adapter = new CourseAdapter(getContext(),courses);
        mRecyclerView.setAdapter(adapter);


        getCourses();

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentUtil.addFragmentWithBackStack(getContext(), new AddCourse(), R.id.container);
            }
        });


        return view;
    }

    protected final SwipeMenuRecyclerView getRecyclerView() {
        return mRecyclerView;
    }


    protected RecyclerView.LayoutManager getLayoutManager() {
        return new LinearLayoutManager(getContext());
    }


    private SwipeMenuCreator mSwipeMenuCreator = new SwipeMenuCreator() {
        @Override
        public void onCreateMenu(SwipeMenu swipeLeftMenu, SwipeMenu swipeRightMenu, int viewType) {
            int width = Utils.dpToPx(getContext(), 100);
            int height = Utils.dpToPx(getContext(), 80);


            {
                SwipeMenuItem deleteItem = new SwipeMenuItem(getContext())
                        .setBackground(R.drawable.select_white)
                        .setImage(R.drawable.delete)
                        .setText(getString(R.string.delete))
                        .setTextColor(Color.WHITE)
                        .setHeight(height)
                        .setWidth(width);
                swipeRightMenu.addMenuItem(deleteItem);


                SwipeMenuItem closeItem = new SwipeMenuItem(getContext())
                        .setBackground(R.drawable.select_red)
                        .setImage(R.drawable.edit_icon)
                        .setWidth(width)
                        .setTextColor(Color.WHITE)
                        .setText(getString(R.string.edit))
                        .setHeight(height);
                swipeRightMenu.addMenuItem(closeItem);

                SwipeMenuItem view = new SwipeMenuItem(getContext())
                        .setBackground(android.R.color.holo_green_light)
                        .setImage(android.R.drawable.ic_dialog_info)
                        .setText(getString(R.string.view))
                        .setTextColor(Color.WHITE)
                        .setHeight(height)
                        .setWidth(width);
                swipeRightMenu.addMenuItem(view);

            }
        }
    };


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            // finish();
        }
        return true;
    }

    private SwipeMenuItemClickListener mMenuItemClickListener = new SwipeMenuItemClickListener() {
        @Override
        public void onItemClick(SwipeMenuBridge menuBridge) {
            menuBridge.closeMenu();

            int direction = menuBridge.getDirection();
            final int adapterPosition = menuBridge.getAdapterPosition();
            int menuPosition = menuBridge.getPosition();



            if (direction == SwipeMenuRecyclerView.RIGHT_DIRECTION) {

                if (menuPosition == 0) {
                    //delete
                    dbFacade.open();
                    dbFacade.deleteCourse(courses.get(adapterPosition).getPk_id()+"");
                    dbFacade.close();
                    getCourses();


                } else if (menuPosition == 1){
                    Fragment fr= new UpdateCourse();
                    Bundle bundle = new Bundle();
                    bundle.putInt(PK_KEY,courses.get(adapterPosition).getPk_id());
                    fr.setArguments(bundle);
                    FragmentUtil.replaceFragment(getContext(),fr,R.id.container);
                    //edit
                }else if(menuPosition==2){
                    Fragment fr = new CourseDetailsFragmennt();
                    Bundle bndle = new Bundle();
                    bndle.putSerializable(PK_KEY,courses.get(adapterPosition));
                    fr.setArguments(bndle);
                    FragmentUtil.replaceFragmentWithBackStack(getContext(),fr,R.id.container);

                }
            }
        }
    };


    private SwipeItemClickListener mItemClickListener = new SwipeItemClickListener() {
        @Override
        public void onItemClick(View itemView, int position) {
            //  Toast.makeText(getApplicationContext(), "mItemClickListener" + position + "个", Toast.LENGTH_SHORT).show();
        }
    };


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
        adapter.notifyDataSetChanged();

        dbFacade.close();

    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getView().setFocusableInTouchMode(true);
        getView().requestFocus();
        getView().setOnKeyListener(new View.OnKeyListener() {

            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_UP && keyCode == KeyEvent.KEYCODE_BACK) {
                    FragmentUtil.replaceFragmentWithBackStack(getActivity(), new MainScreen(), R.id.container);
                    return true;

                }
                return false;
            }
        });

    }


}
