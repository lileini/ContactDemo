package com.example.administrator.contactdemo.gruc;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.administrator.contactdemo.contact.Phone;

import java.util.List;

public class GrucDao {
    private Context context;
    private String TAG = "GrucDao";

    public GrucDao(Context context) {
        this.context = context;

    }

    public void saveGruc(ObjectsEntity gruc){
        GrucDbHelper dbHelper = new GrucDbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.beginTransaction();
        db.execSQL("insert into "+ dbHelper.getTableName() +"(_icon_url,_mobile,_name," +
                "_nickname,_email)"+" value(?,?,?,?,?)"
                ,new Object[]{gruc.getIcon_url(),gruc.getMobile(),gruc.getName(),gruc.getNickname(),gruc.getEmail()});
        // database.close();可以不关闭数据库，他里面会缓存一个数据库对象，如果以后还要用就直接用这个缓存的数据库对象。但通过
        // context.openOrCreateDatabase(arg0, arg1, arg2)打开的数据库必须得关闭
        db.setTransactionSuccessful();
        db.endTransaction();
    }

    public void saveGrucList(List<ObjectsEntity> grucList){
//        Log.i(TAG,"grucList.toString() = "+grucList.toString());
        GrucDbHelper dbHelper = new GrucDbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.beginTransaction();
        for (ObjectsEntity gruc:grucList){
            db.execSQL("insert into "+ dbHelper.getTableName() +"(_icon_url,_mobile,_name," +
                            "_nickname,_email)"+" value(?,?,?,?,?)"
                    ,new Object[]{gruc.getIcon_url(),gruc.getMobile(),gruc.getName(),gruc.getNickname(),gruc.getEmail()});
            // database.close();可以不关闭数据库，他里面会缓存一个数据库对象，如果以后还要用就直接用这个缓存的数据库对象。但通过
            // context.openOrCreateDatabase(arg0, arg1, arg2)打开的数据库必须得关闭

        }
        db.setTransactionSuccessful();
        db.endTransaction();
    }
    public void updateGruc(ObjectsEntity gruc) {
        GrucDbHelper dbHelper = new GrucDbHelper(context);
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("_icon_url", gruc.getIcon_url());
        contentValues.put("_mobile", gruc.getMobile());
        contentValues.put("_name", gruc.getName());
        contentValues.put("_nickname", gruc.getNickname());
        contentValues.put("_email", gruc.getEmail());
        database.update(dbHelper.getTableName(), contentValues, "_id=?",
                new String[]{String.valueOf(gruc.getId())});
        dbHelper.close();
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
