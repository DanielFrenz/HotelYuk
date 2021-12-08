package com.example.hotelyuk.preferences;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.hotelyuk.entity.User;

public class UserPreferences {
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    Context context;

    public static final String IS_LOGIN = "isLogin";
    public static final String KEY_ID = "id";
    public static final String KEY_USERNAME = "username";
    public static final String KEY_EMAIL = "email";
    public static final String KEY_PASSWORD = "password";
    public static final String KEY_NO_TELP = "no_telp";
    public static final String KEY_IMG_URL = "img_url";
    public static final String ACCESS_TOKEN = "access_token";

    public UserPreferences(Context context) {
        this.context = context;
        sharedPreferences = context.getSharedPreferences("userPreferences",Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    public void setUser(Long id, String username, String email, String password, String no_telp, String img_url, String access_token){
        editor.putBoolean(IS_LOGIN, true);
        editor.putLong(KEY_ID,id);
        editor.putString(KEY_USERNAME,username);
        editor.putString(KEY_EMAIL,email);
        editor.putString(KEY_PASSWORD,password);
        editor.putString(KEY_NO_TELP,no_telp);
        editor.putString(KEY_IMG_URL,img_url);
        editor.putString(ACCESS_TOKEN, access_token);
        editor.commit();
    }

    public User getUserLogin(){
        String username,email,password,no_telp,img_url;
        Long id;

        id = sharedPreferences.getLong(KEY_ID,0);
        username = sharedPreferences.getString(KEY_USERNAME,null);
        email = sharedPreferences.getString(KEY_EMAIL,null);
        password = sharedPreferences.getString(KEY_PASSWORD,null);
        no_telp = sharedPreferences.getString(KEY_NO_TELP,null);
        img_url = sharedPreferences.getString(KEY_IMG_URL, null);
        User user = new User(username,email,password,no_telp,img_url);
        user.setId(id);
        return user;
    }

    public void setKeyUsername(String username) {
        editor.putString(KEY_USERNAME, username);
        editor.commit();
    }

    public void setKeyEmail(String email) {
        editor.putString(KEY_EMAIL, email);
        editor.commit();
    }

    public void setKeyPassword(String password) {
        editor.putString(KEY_PASSWORD, password);
        editor.commit();
    }

    public void setKeyNoTelp(String no_telp) {
        editor.putString(KEY_NO_TELP, no_telp);
        editor.commit();
    }

    public void setKeyImgUrl(String img_url){
        editor.putString(KEY_IMG_URL, img_url);
        editor.commit();
    }

    public String getAccessToken(){
        return sharedPreferences.getString(ACCESS_TOKEN, null);
    }

    public boolean checkLogin(){
        return sharedPreferences.getBoolean(IS_LOGIN,false);
    }

    public void logout(){
        editor.clear();
        editor.commit();
    }
}
