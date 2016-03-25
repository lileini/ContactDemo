package com.example.administrator.contactdemo.observer;

import android.app.Activity;
import android.content.Loader;
import android.database.ContentObserver;
import android.os.Handler;

import com.example.administrator.contactdemo.contact.PhonesLoader;

public class ContactObserver extends ContentObserver {
    public boolean isAllowUpdate = true;
    /**
     * Creates a content observer.
     *
     * @param handler The handler to run {@link #onChange} on, or null if none.
     */
    Activity context;
    public ContactObserver(Activity context,Handler handler){
        super(handler);
        this.context = context;
    }
    int i = 1;
    @Override
    public void onChange(boolean selfChange) {
        super.onChange(selfChange);
        if (isAllowUpdate){
            isAllowUpdate = false;
            Loader loader = context.getLoaderManager().initLoader(i++, null, PhonesLoader.getInstance(context));
            loader.startLoading();
        }
    }
}
