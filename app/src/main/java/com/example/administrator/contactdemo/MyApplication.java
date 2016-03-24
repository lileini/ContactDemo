package com.example.administrator.contactdemo;

import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import android.util.Log;

import com.example.administrator.contactdemo.contact.CountryCodeDao;
import com.example.administrator.contactdemo.util.SharePrefrenceManager;

import org.xutils.x;

public class MyApplication extends Application{
    public static MyApplication instance;
    private final String TAG = "MyApplication";
    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        init();
    }

    private void init() {
        if (new SharePrefrenceManager(this).getIsFirstLoad()) {
            Log.i(TAG,"init");
            new CountryCodeDao(this).execute();
        }

        x.Ext.init(this);
        x.Ext.setDebug(true);
    }

    public static MyApplication getInstance(){
        return instance;
    }

    /**
     * @description

     * @return 得到需要分配的缓存大小，这里用八分之一的大小来做图片缓存
     */
    public int getMemoryCacheSize() {
        // Get memory class of this device, exceeding this amount will throw an
        // OutOfMemory exception.
        final int memClass = ((ActivityManager)getSystemService(Context.ACTIVITY_SERVICE)).getMemoryClass();

        // Use 1/8th of the available memory for this memory cache.
        return 1024 * 1024 * memClass / 8;
    }

}
