package com.example.islam.trainingcenter.app.Module.Adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.islam.trainingcenter.DB.Tables.TAds;
import com.example.islam.trainingcenter.DB.Tables.TCourse;
import com.example.islam.trainingcenter.R;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by islam on 11/10/2017.
 */

public class AdsAdapter extends RecyclerView.Adapter<AdsAdapter.MmyviewHolder> {
    Context context ;
    List <TAds> mList;

    public AdsAdapter(Context context, List<TAds> mList) {
        this.context = context;
        this.mList = mList;
    }

    @Override
    public MmyviewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MmyviewHolder(LayoutInflater.from(context).inflate(R.layout.item_ads_new,parent,false));
    }

    @Override
    public void onBindViewHolder(MmyviewHolder holder, int position) {
        byte [] image =mList.get(position).getImagePath();
        holder.title.setText(mList.get(position).getTitle());
        Bitmap bmp = BitmapFactory.decodeByteArray(image, 0, image.length);

        holder.img.setImageBitmap(bmp);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class MmyviewHolder extends RecyclerView.ViewHolder {
        TextView title ;
        ImageView img ;
        public MmyviewHolder(View itemView) {
            super(itemView);
            title= itemView.findViewById(R.id.title);
            img= itemView.findViewById(R.id.img);

        }
    }
}
