package com.example.islam.trainingcenter.Utilites;

import android.text.Editable;
import android.text.TextUtils;
import android.util.Patterns;

import java.util.regex.Pattern;

/**
 * Created by islam on 11/8/2017.
 */

public class CheckUtil {

    private static final int MIN_PASSWORD_LENGTH=8;
    private static final int PHONE_NUMBER_LENGTH=10;

    public static boolean checkEmail(CharSequence email) {
        if (TextUtils.isEmpty(email)) {
            return false;
        } else {
            return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
        }
    }

// boolean checkPass
    public static boolean checkPass(CharSequence pass) {
        if (pass.length() < MIN_PASSWORD_LENGTH) {
            return false;
        } else {
            return true;
        }
    }

    public static boolean checkPhone(CharSequence phone) {
        if (TextUtils.isDigitsOnly(phone)&&phone.length()==PHONE_NUMBER_LENGTH)
            return true;
        else
            return false;

    }
}
