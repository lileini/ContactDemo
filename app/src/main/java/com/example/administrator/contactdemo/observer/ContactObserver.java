package com.example.administrator.contactdemo.observer;

import android.app.Activity;
import android.database.ContentObserver;
import android.os.Handler;
import android.util.Log;

public class ContactObserver extends ContentObserver {
    private static final String TAG = "ContactObserver";
    public boolean isAllowUpdate = true;
    /**
     * Creates a content observer.
     *
     * @param handler The handler to run {@link #onChange} on, or null if none.
     */
    Activity context;
    Handler handler;
    public ContactObserver(Activity context,Handler handler){
        super(handler);
        this.context = context;
        this.handler = handler;
    }
    int i = 1;
    @Override
    public void onChange(boolean selfChange) {
        super.onChange(selfChange);
        if (isAllowUpdate){
            isAllowUpdate = false;
            handler.sendEmptyMessageDelayed(0,1000);
            Log.i(TAG,"联系人改变");
        }
    }
}
