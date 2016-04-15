package com.example.administrator.contactdemo.gruc;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class GrucDbHelper extends SQLiteOpenHelper{
    private static final String name = "demoDB";
    private String TABLE_NAME = "gruc";
    private static final int version = 1;
    private Context context;
    public GrucDbHelper(Context context) {
        super(context, name, null, version);
        this.context = context;
    }

//    private String email;
//    private boolean enable;
//    private Object icon_url;
//    private String id;
//    private String mobile;
//    private String name;
//    private Object nickname;
    @Override
    public void onCreate(SQLiteDatabase db) {
        String s = "create table if not exists "+ TABLE_NAME+"(_id integer primary key autoincrement," +
                "_icon_url integer,_mobile text,_name text,_nickname text," +
                "_email text)";
        db.execSQL(s);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "DROP TABLE IF EXISTS " + TABLE_NAME;
        db.execSQL(sql);
        db.close();
        GrucDbHelper grucDbHelper = new GrucDbHelper(context);
        String s = "create table if not exists "+ TABLE_NAME+"(_id integer primary key autoincrement," +
                "_icon_url integer,_mobile text,_name text,_nickname text," +
                "_email text)";
        SQLiteDatabase writableDatabase = grucDbHelper.getWritableDatabase();
        writableDatabase.execSQL(s);
        writableDatabase.close();
    }
    public String getTableName(){
        return  TABLE_NAME;
    }

    public void setTableName(String tableName){
        TABLE_NAME = tableName;
    }

    public void updateTable(String tableName){
        if (tableName == null)
            tableName = TABLE_NAME;
        String sql = "DROP TABLE IF EXISTS " + TABLE_NAME;

        GrucDbHelper grucDbHelper1 = new GrucDbHelper(context);
        SQLiteDatabase db =grucDbHelper1.getWritableDatabase();
        db.execSQL(sql);
//        db.close();
        /*GrucDbHelper grucDbHelper = new GrucDbHelper(context);
        SQLiteDatabase db =grucDbHelper.getWritableDatabase();
        String s = "create table "+ TABLE_NAME+"(_id integer primary key autoincrement," +
                "_icon_url integer,_mobile text,_name text,_nickname text," +
                "_email text)";
        db.execSQL(s);*/
    }
}
