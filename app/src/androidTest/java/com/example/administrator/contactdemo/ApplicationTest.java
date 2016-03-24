package com.example.administrator.contactdemo;

import android.app.Application;
import android.test.ApplicationTestCase;
import android.util.Log;

import com.github.promeg.pinyinhelper.Pinyin;

/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
public class ApplicationTest extends ApplicationTestCase<Application> {

    private static final String TAG = "ApplicationTest";

    public ApplicationTest() {
        super(Application.class);
    }
    public void testT(){
        boolean s = Pinyin.isChinese('s');
        Log.i(TAG, "s = " + s);
        boolean b = Pinyin.isChinese('中');
        Log.i(TAG, "b = " + b);
        String s1 = Pinyin.toPinyin('綦');
        Log.i(TAG,"s1 = "+ s1);
    }
}