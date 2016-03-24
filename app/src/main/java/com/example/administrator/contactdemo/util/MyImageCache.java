package com.example.administrator.contactdemo.util;

import android.graphics.Bitmap;
import android.util.Log;
import android.util.LruCache;

public class MyImageCache extends LruCache<Integer,Bitmap> {
    /**
     * @param maxSize for caches that do not override {@link #sizeOf}, this is
     *                the maximum number of entries in the cache. For all other caches,
     *                this is the maximum sum of the sizes of the entries in this cache.
     */
    public MyImageCache(int maxSize) {
        super(maxSize);
    }
    @Override
    protected Bitmap create(Integer key) {
        return null;
    }

    @Override
    protected void entryRemoved(boolean evicted, Integer key, Bitmap oldValue, Bitmap newValue) {
        super.entryRemoved(evicted, key, oldValue, newValue);
        if (evicted) {
            if (oldValue != null && !oldValue.isRecycled()) {

                Log.i("entryRemoved", "isRecycled");
                oldValue.recycle();
                oldValue = null;
            }
            Log.i("entryRemoved", "key == " + key);
        }
    }

}
