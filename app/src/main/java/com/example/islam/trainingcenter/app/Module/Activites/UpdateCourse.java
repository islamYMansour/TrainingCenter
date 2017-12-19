package com.example.islam.trainingcenter.app.Module.Activites;

import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.islam.trainingcenter.Base.BaseFragment;
import com.example.islam.trainingcenter.DB.DBFacade;
import com.example.islam.trainingcenter.DB.SQLiteHelper;
import com.example.islam.trainingcenter.DB.Tables.TCourse;
import com.example.islam.trainingcenter.R;
import com.example.islam.trainingcenter.Utilites.Utils;

/**
 * Created by islam on 12/17/2017.
 */

public class UpdateCourse extends BaseFragment {

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
        add.setText("تعديل");
        final int key = getArguments().getInt(ManageCourseActivity.PK_KEY);

        dbFacade.open();
        Cursor cursor = dbFacade.getCourseByColumn(SQLiteHelper.PK_ID_COULMN, key + "");
        while (cursor.moveToNext()) {
            trainer.setText(cursor.getString(cursor.getColumnIndex(SQLiteHelper.COURSE_COURSETRAINER_COLUMN)));
            time.setText(cursor.getString(cursor.getColumnIndex(SQLiteHelper.COURSE_DATE_COULMN)));
            hours.setText(cursor.getString(cursor.getColumnIndex(SQLiteHelper.COURSE_HOURS_COLUMN)));
            title.setText(cursor.getString(cursor.getColumnIndex(SQLiteHelper.COURSE_NAME_COLUMN)));
            place.setText(cursor.getString(cursor.getColumnIndex(SQLiteHelper.COURSE_PLACE_COLUMN)));
            price.setText(cursor.getString(cursor.getColumnIndex(SQLiteHelper.COURSE_PRICE_COLUMN)));
        }

        dbFacade.close();

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


                dbFacade.updateCourse(course, key + "");
                dbFacade.close();

                getFragmentManager().popBackStack();
            }
        });


        return view;
    }


}
