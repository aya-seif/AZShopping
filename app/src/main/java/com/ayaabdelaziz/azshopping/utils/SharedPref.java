package com.ayaabdelaziz.azshopping.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPref {

    Context context;
    SharedPreferences sharedPreferences;
    private final SharedPreferences.Editor editor;

    public SharedPref(Context context) {
        this.context = context;
        sharedPreferences = this.context.getSharedPreferences("my_sharedpref", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    public void saveUserName(String name){
        editor.putString("username",name);
        editor.apply();
    }

    public String getUserName(){
        return sharedPreferences.getString("username",null);
    }

    public void saveEmail(String email){
        editor.putString("email",email);
        editor.apply();
    }

    public String getEmail(){
        return sharedPreferences.getString("email",null);
    }
    public void savePassword(String pass){
        editor.putString("pass",pass);
        editor.apply();
    }

    public String getPass(){
        return sharedPreferences.getString("pass",null);
    }




}
