package com.example.islam.trainingcenter.app.Module.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.islam.trainingcenter.DB.Tables.TCourse;
import com.example.islam.trainingcenter.R;

import java.util.ArrayList;

/**
 * Created by islam on 11/10/2017.
 */

public class CourseAdapter extends RecyclerView.Adapter<CourseAdapter.MmyviewHolder> {
    Context context ;
    ArrayList <TCourse> mList;

    public CourseAdapter(Context context, ArrayList<TCourse> mList) {
        this.context = context;
        this.mList = mList;
    }

    @Override
    public MmyviewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MmyviewHolder(LayoutInflater.from(context).inflate(R.layout.course_item,parent,false));
    }

    @Override
    public void onBindViewHolder(MmyviewHolder holder, int position) {

        holder.title.setText(mList.get(position).getName());
        holder.courseTrainer.setText(mList.get(position).getCourseTrainer());
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class MmyviewHolder extends RecyclerView.ViewHolder {
        TextView title , courseTrainer ;
        public MmyviewHolder(View itemView) {
            super(itemView);
            title= itemView.findViewById(R.id.title);
            courseTrainer= itemView.findViewById(R.id.course_trainer);

        }
    }
}
