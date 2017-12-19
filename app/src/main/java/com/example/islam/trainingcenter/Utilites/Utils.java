package com.example.islam.trainingcenter.Utilites;

import android.content.ContentResolver;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.text.TextUtils;

import com.example.islam.trainingcenter.Base.BaseActivity;
import com.example.islam.trainingcenter.app.Constants;
import com.example.islam.trainingcenter.app.Module.Activites.LoginActivity;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by islam on 11/7/2017.
 */

public class Utils {

    public static String encrypt(String password) {
        MessageDigest md = null;
        String encryptedString = null;
        if (TextUtils.isEmpty(password.trim()))
            return encryptedString;
        else {
            try {
                md = MessageDigest.getInstance("MD5");
                md.update(password.getBytes());
                byte[] digest = md.digest();
                encryptedString = new BigInteger(1, digest).toString(16);
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
            return encryptedString;
        }
    }


    public static String getPath(Context context,Uri uri) {
        String[] projection = {MediaStore.Images.Media.DATA};
        Cursor cursor = ((BaseActivity)context).managedQuery(uri, projection, null, null, null);
        if (cursor != null) {
            //HERE YOU WILL GET A NULLPOINTER IF CURSOR IS NULL
            //THIS CAN BE, IF YOU USED OI FILE MANAGER FOR PICKING THE MEDIA
            int column_index = cursor
                    .getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            cursor.moveToFirst();
            return cursor.getString(column_index);
        } else return null;
    }


    public static void saveUser(Context context,String data){
        SharedPreferences preferences = context.getSharedPreferences(Constants.USER_SHARED_PREFERNCE,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(Constants.USER_PHONE_PREFERNCE,data);


        editor.commit();
    }

    public static void removeUser(Context context){
        SharedPreferences preferences = context.getSharedPreferences(Constants.USER_SHARED_PREFERNCE,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.clear();
        editor.commit();
    }

    public static String getUser(Context context){
        SharedPreferences preferences = context.getSharedPreferences(Constants.USER_SHARED_PREFERNCE,Context.MODE_PRIVATE);
        return preferences.getString(Constants.USER_PHONE_PREFERNCE,"");
    }


    public static int dpToPx(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    public static byte[] convertImageToByte(Context context,Uri uri){
        byte[] data = null;
        try {
            ContentResolver cr = context.getContentResolver();
            InputStream inputStream = cr.openInputStream(uri);
            Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
            data = baos.toByteArray();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return data;
    }


}
