package com.example.islam.trainingcenter.app.Module.Activites;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.islam.trainingcenter.Base.BaseFragment;
import com.example.islam.trainingcenter.DB.Tables.TCourse;
import com.example.islam.trainingcenter.R;

/**
 * Created by islam on 12/18/2017.
 */

public class CourseDetailsFragmennt extends BaseFragment{

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.course_details,container,false);
        EditText title = view.findViewById(R.id.course_title);
        EditText date = view.findViewById(R.id.course_date);
        EditText place = view.findViewById(R.id.course_place);
        EditText price = view.findViewById(R.id.course_price);
        EditText trainer = view.findViewById(R.id.course_trainer);

        try {
            TCourse course = (TCourse) getArguments().get(ManageCourseActivity.PK_KEY);

            title.setText(course.getName());
            date.setText(course.getDate());
            place.setText(course.getPlace());
            price.setText(course.getPrice());
            trainer.setText(course.getCourseTrainer());

        }catch (NullPointerException ex){
            Log.d("exception",ex.toString());
        }


        return view;
    }
}
