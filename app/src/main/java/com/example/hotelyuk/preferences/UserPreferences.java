package com.example.hotelyuk.preferences;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.hotelyuk.room.model.User;

public class UserPreferences {
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    Context context;

    public static final String IS_LOGIN = "isLogin";
    public static final String KEY_ID = "id";
    public static final String KEY_EMAIL = "email";
    public static final String KEY_PASSWORD = "password";
    public static final String KEY_USERNAME = "username";
    public static final String KEY_NO_TELP = "no_telp";

    public UserPreferences(Context context) {
        this.context = context;
        sharedPreferences = context.getSharedPreferences("userPreferences",Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    public void setUser(int id,String email, String password, String username, String no_telp){
        editor.putBoolean(IS_LOGIN, true);
        editor.putInt(KEY_ID,id);
        editor.putString(KEY_EMAIL,email);
        editor.putString(KEY_PASSWORD,password);
        editor.putString(KEY_USERNAME,username);
        editor.putString(KEY_NO_TELP,no_telp);
        editor.commit();
    }

    public User getUserLogin(){
        String email,password,username,no_telp;
        int id;

        id = sharedPreferences.getInt(KEY_ID,0);
        email = sharedPreferences.getString(KEY_EMAIL,null);
        password = sharedPreferences.getString(KEY_PASSWORD,null);
        username = sharedPreferences.getString(KEY_USERNAME,null);
        no_telp = sharedPreferences.getString(KEY_NO_TELP,null);
        return new User(id,email,password,username,no_telp);
    }

    public boolean checkLogin(){
        return sharedPreferences.getBoolean(IS_LOGIN,false);
    }

    public void logout(){
        editor.clear();
        editor.commit();
    }
}
