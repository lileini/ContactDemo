package com.example.administrator.contactdemo;

import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import android.os.PowerManager;
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
        acquireWakeLock();
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
    PowerManager.WakeLock wakeLock = null;
    //获取电源锁，保持该服务在屏幕熄灭时仍然获取CPU时，保持运行
    private void acquireWakeLock(){
        if (null == wakeLock){
            PowerManager pm = (PowerManager)this.getSystemService(Context.POWER_SERVICE);
            wakeLock = pm.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, "PostLocationService");
            if (null != wakeLock){
                wakeLock.acquire();
            }
        }
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        releaseWakeLock();
    }

    //释放设备电源锁
    private void releaseWakeLock(){
        if (null != wakeLock){
            wakeLock.release();
            wakeLock = null;
        }
    }

}
