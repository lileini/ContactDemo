package com.example.administrator.contactdemo.contact;

import com.example.administrator.contactdemo.MyApplication;
import com.lidroid.xutils.DbUtils;

/**
 * Created by Administrator on 2015/12/3 0003.
 */
public class DBManager{

    private static DbUtils db;

    public static DbUtils getDbUtils(){
        if (db == null)
            db = DbUtils.create(MyApplication.getInstance());
       return  db;
    }


}
