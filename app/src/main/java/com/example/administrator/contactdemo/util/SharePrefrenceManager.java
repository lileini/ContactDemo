package com.example.administrator.contactdemo.util;

import android.content.Context;
import android.content.SharedPreferences;

public class SharePrefrenceManager {
    private final String IS_FIRST_LOAD = "isFirstLoad";
    private final String ACCESS_TOKEN = "ACCESS_TOKEN";
    private final String SHARE_NAME = "GRUC";
    private Context context;

    public SharePrefrenceManager(Context context){
     this.context = context;
    }
    public void saveIsFirstLoad(boolean isFirstLoad){
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(IS_FIRST_LOAD, isFirstLoad);
        editor.commit();
    }
    public boolean getIsFirstLoad(){
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARE_NAME, Context.MODE_PRIVATE);
        return  sharedPreferences.getBoolean(IS_FIRST_LOAD,true);
    }
    public void saveAccessToken(String access_token){
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(ACCESS_TOKEN, access_token);
        editor.commit();
    }
    public String getAccessToken(){
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARE_NAME, Context.MODE_PRIVATE);
        return  sharedPreferences.getString(ACCESS_TOKEN,"");
    }

}
