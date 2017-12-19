package com.example.islam.trainingcenter.Utilites;

import android.content.Context;
import android.content.res.Resources;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.drawable.ProgressBarDrawable;
import com.facebook.drawee.drawable.ScalingUtils;
import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.generic.GenericDraweeHierarchyBuilder;
import com.facebook.imagepipeline.core.ImagePipelineConfig;
import com.facebook.imagepipeline.listener.RequestListener;
import com.facebook.imagepipeline.listener.RequestLoggingListener;

import java.util.HashSet;
import java.util.Set;


/**
 * Created by ahmed on 12/10/2016.
 */

public class FrescoHelper {

    public static void init(Context ctx){
        Set<RequestListener> listeners = new HashSet<>();
        listeners.add(new RequestLoggingListener());
        ImagePipelineConfig config = ImagePipelineConfig.newBuilder(ctx)
                .setDownsampleEnabled(true)
                .setRequestListeners(listeners)

                .build();
        Fresco.initialize(ctx,config);
    }

    public static GenericDraweeHierarchy getItemHierarchy(Resources res, ScalingUtils.ScaleType scaleType){
        if(scaleType==null)
            scaleType= ScalingUtils.ScaleType.CENTER_CROP;
        ProgressBarDrawable mProgressBarDrawable = new ProgressBarDrawable();
        mProgressBarDrawable.setPadding(0);
        mProgressBarDrawable.setBarWidth(5);
        mProgressBarDrawable.setHideWhenZero(true);
        mProgressBarDrawable.setColor(0xFFF6EB1B);
        return new GenericDraweeHierarchyBuilder(res)
                .setProgressBarImage(mProgressBarDrawable)

                //.setFailureImage(ResourcesCompat.getDrawable(res, R.mipmap.img_item_fail, null), scaleType)
               // .setPlaceholderImage(ResourcesCompat.getDrawable(res, R.drawable.progress_color, null))
                .setActualImageScaleType(scaleType)
                .setFadeDuration(200)
                .build();

    }
}
