package com.example.islam.trainingcenter.app.Module.Adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.islam.trainingcenter.DB.Tables.TCourse;
import com.example.islam.trainingcenter.R;
import com.example.islam.trainingcenter.Utilites.FragmentUtil;
import com.example.islam.trainingcenter.app.Module.Activites.CourseDetailsFragmennt;
import com.example.islam.trainingcenter.app.Module.Activites.ManageCourseActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by islam on 12/18/2017.
 */

public class TrianeeCourseAdapter extends RecyclerView.Adapter<TrianeeCourseAdapter.MmyviewHolder> {
    Context context;
    List<TCourse> mList;

    public TrianeeCourseAdapter(Context context, List<TCourse> mList) {
        this.context = context;
        this.mList = mList;
    }

    @Override
    public TrianeeCourseAdapter.MmyviewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new TrianeeCourseAdapter.MmyviewHolder(LayoutInflater.from(context).inflate(R.layout.item_course_list, parent, false));
    }

    @Override
    public void onBindViewHolder(TrianeeCourseAdapter.MmyviewHolder holder, final int position) {

        try {


            holder.title.setText(mList.get(position).getName());
            holder.courseTrainer.setText(mList.get(position).getCourseTrainer());
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Fragment fr = new CourseDetailsFragmennt();
                    Bundle bundle = new Bundle();
                    bundle.putSerializable(ManageCourseActivity.PK_KEY, mList.get(position));
                    fr.setArguments(bundle);
                    FragmentUtil.replaceFragmentWithBackStack(context, fr, R.id.container);
                }
            });
        } catch (Exception ex) {

        }
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class MmyviewHolder extends RecyclerView.ViewHolder {
        TextView title, courseTrainer;

        public MmyviewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.course_title);
            courseTrainer = itemView.findViewById(R.id.course_trainer);

        }
    }
}
