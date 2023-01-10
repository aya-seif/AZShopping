package com.ayaabdelaziz.azshopping.utils;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.widget.ImageView;

import java.io.ByteArrayOutputStream;

public class TypeConverter {

    public static byte[] convertBitmapToByteArray(Bitmap view) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        view.compress(Bitmap.CompressFormat.PNG, 100, baos);
        byte[] imageBytes = baos.toByteArray();
        return imageBytes;
    }


    public static int convertToIntAdd(String value) {
        return Integer.parseInt(value) + 1;
    }

    public static int convertToIntminus(String value) {
        return Integer.parseInt(value) - 1;
    }

    public static String convertToString(int value) {
        return String.valueOf(value);
    }

    public static int NUMBER;

    public static void callsharedPreferences(Context context) {
//        SharedPreferences sharedPreferences = context.getSharedPreferences("MySharedPref", MODE_PRIVATE);
//        SharedPreferences.Editor myEdit = sharedPreferences.edit();
//
//        myEdit.putString("name", name.getText().toString());
//        myEdit.putInt("age", Integer.parseInt(age.getText().toString()));
//        myEdit.commit();

    }


}
