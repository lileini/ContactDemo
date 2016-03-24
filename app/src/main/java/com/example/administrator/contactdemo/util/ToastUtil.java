package com.example.administrator.contactdemo.util;

import android.content.Context;
import android.widget.Toast;

public class ToastUtil {
    public final int SHOW_LONG = Toast.LENGTH_LONG;
    public final int SHOW_SHORT = Toast.LENGTH_SHORT;

    /**
     *
     * @param context
     * @param content
     * @param time ToastUitl.SHOW_LONG or ToastUitl.SHOW_SHORT
     */
    public static void showToast(Context context, String content, int time){
        Toast.makeText(context,content,time).show();
    }
}
