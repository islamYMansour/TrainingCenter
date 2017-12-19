package com.example.islam.trainingcenter.app.Module.Activites;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.islam.trainingcenter.Base.BaseFragment;
import com.example.islam.trainingcenter.DB.DBFacade;
import com.example.islam.trainingcenter.DB.Tables.TAds;
import com.example.islam.trainingcenter.DB.Tables.TCenter;
import com.example.islam.trainingcenter.DB.Tables.TCourse;
import com.example.islam.trainingcenter.R;
import com.example.islam.trainingcenter.Utilites.FragmentUtil;
import com.example.islam.trainingcenter.Utilites.Utils;
import com.facebook.drawee.view.SimpleDraweeView;

/**
 * Created by islam on 12/17/2017.
 */

public class AddAd extends BaseFragment {

    private static final int PICK_IMAGE = 1;
    private static final String SELETED_IMAGE = "selected image";
    private SimpleDraweeView profilePic;
    private Uri selectedImageUri;
    private byte[] selectedImagePath;
    private ImageView img;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.add_new_ads, container, false);
        img = view.findViewById(R.id.img);
        Button add = view.findViewById(R.id.add);
        final EditText title = view.findViewById(R.id.course_title);
        final DBFacade db = new DBFacade(getContext());
        Button choose = view.findViewById(R.id.choose_img);


        choose.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, SELETED_IMAGE), PICK_IMAGE);


            }
        });

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db.open();
                TCenter aAd = new TAds.AdsBuilder()
                        .setImagePath(selectedImagePath)
                        .setTitle(title.getText().toString())
                        .build();
                db.insertAds(aAd);
                db.close();

                FragmentUtil.replaceFragment(getContext(), new ManageAds(), R.id.container);

            }
        });


        return view;
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == PICK_IMAGE) {
            selectedImageUri = data.getData();
            selectedImagePath = Utils.convertImageToByte(getContext(), selectedImageUri);
            try {
                Bitmap bmp = BitmapFactory.decodeByteArray(selectedImagePath, 0, selectedImagePath.length);
                img.setImageBitmap(bmp);
            } catch (Exception ex) {

            }
        }
    }
}
