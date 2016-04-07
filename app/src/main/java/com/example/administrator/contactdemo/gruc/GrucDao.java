package com.example.administrator.contactdemo.gruc;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.administrator.contactdemo.contact.Phone;

import java.util.List;

public class GrucDao {
    private final GrucDbHelper dbHelper;

    public GrucDao(Context context) {
        dbHelper = new GrucDbHelper(context);
    }

    public void saveGruc(ObjectsEntity gruc){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.beginTransaction();
        db.execSQL("insert into "+ dbHelper.getTableName() +"(_icon_url integer,_mobile text,_name text," +
                "_nickname text,_email text)"+" value(?,?,?,?,?)"
                ,new Object[]{gruc.getIcon_url(),gruc.getMobile(),gruc.getName(),gruc.getNickname(),gruc.getEmail()});
        // database.close();可以不关闭数据库，他里面会缓存一个数据库对象，如果以后还要用就直接用这个缓存的数据库对象。但通过
        // context.openOrCreateDatabase(arg0, arg1, arg2)打开的数据库必须得关闭
        db.setTransactionSuccessful();
        db.endTransaction();
    }

    public void saveGrucList(List<ObjectsEntity> grucList){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.beginTransaction();
        for (ObjectsEntity gruc:grucList){
            db.execSQL("insert into "+ dbHelper.getTableName() +"(_icon_url integer,_mobile text,_name text," +
                    "_nickname text,_email text)"+" value(?,?,?,?,?)"
                    ,new Object[]{gruc.getIcon_url(),gruc.getMobile(),gruc.getName(),gruc.getNickname(),gruc.getEmail()});
            // database.close();可以不关闭数据库，他里面会缓存一个数据库对象，如果以后还要用就直接用这个缓存的数据库对象。但通过
            // context.openOrCreateDatabase(arg0, arg1, arg2)打开的数据库必须得关闭

        }
        db.setTransactionSuccessful();
        db.endTransaction();
    }
    public void updateGruc(ObjectsEntity gruc) {
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("_icon_url", gruc.getIcon_url());
        contentValues.put("_mobile", gruc.getMobile());
        contentValues.put("_name", gruc.getName());
        contentValues.put("_nickname", gruc.getNickname());
        contentValues.put("_email", gruc.getEmail());
        database.update(dbHelper.getTableName(), contentValues, "_id=?",
                new String[]{String.valueOf(gruc.getId())});
    }
    /*public void deletePhone(Phone phone){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.beginTransaction();
        db.execSQL("delete");
        // database.close();可以不关闭数据库，他里面会缓存一个数据库对象，如果以后还要用就直接用这个缓存的数据库对象。但通过
        // context.openOrCreateDatabase(arg0, arg1, arg2)打开的数据库必须得关闭
        db.setTransactionSuccessful();
        db.endTransaction();
    }*/
    public List<Phone> queryGruc(){
        return null;
    }
}
