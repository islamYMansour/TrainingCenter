package com.example.islam.trainingcenter.app.Module.Activites;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.islam.trainingcenter.Base.BaseFragment;
import com.example.islam.trainingcenter.DB.DBFacade;
import com.example.islam.trainingcenter.DB.Tables.TCenter;
import com.example.islam.trainingcenter.DB.Tables.TCourse;
import com.example.islam.trainingcenter.R;
import com.example.islam.trainingcenter.Utilites.FragmentUtil;
import com.example.islam.trainingcenter.Utilites.Utils;
import com.example.islam.trainingcenter.app.Module.Dialogs.Dialog;

/**
 * Created by islam on 12/17/2017.
 */

public class AddCourse extends BaseFragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_add_course, container, false);

        Button add = view.findViewById(R.id.add);
        final EditText title = view.findViewById(R.id.coursetitle);
        final EditText trainer = view.findViewById(R.id.course_trainer);
        final EditText hours = view.findViewById(R.id.course_hours);
        final EditText price = view.findViewById(R.id.course_price);
        final EditText time = view.findViewById(R.id.course_date);
        final EditText place = view.findViewById(R.id.course_place);

        final DBFacade dbFacade = new DBFacade(getContext());

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dbFacade.open();
                TCourse course = new TCourse.CourseBuilder()
                        .setCourseTrainer(trainer.getText().toString())
                        .setDate(time.getText().toString())
                        .setForignKey(Utils.getUser(getContext()))
                        .setHoursCount(hours.getText().toString())
                        .setName(title.getText().toString())
                        .setPlace(place.getText().toString())
                        .setPrice(price.getText().toString()).build();


                dbFacade.insertCourse(course);
                dbFacade.close();

                FragmentUtil.replaceFragment(getContext(),new ManageCourseActivity(),R.id.container);
            }


        });


        return view;
    }
}
