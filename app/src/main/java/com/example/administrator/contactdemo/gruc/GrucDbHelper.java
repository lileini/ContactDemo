package com.example.administrator.contactdemo.gruc;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.text.TextUtils;

public class GrucDbHelper extends SQLiteOpenHelper{
    private static final String name = "demoDB";
    private String TABLE_NAME = "gruc";
    private static final int version = 1;
    private Context context;
    private static GrucDbHelper dbHelper;
    public static synchronized GrucDbHelper getInstance(Context context,String table_name){
        if (dbHelper == null){
            dbHelper = new GrucDbHelper(context,table_name);
        }
        return dbHelper;
    }
    private GrucDbHelper(Context context,String table_name){
        super(context, name, null, version);
        this.context = context;
        if (!TextUtils.isEmpty(table_name))
            this.TABLE_NAME = table_name;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String s = "create table if not exists "+ TABLE_NAME+"(_id integer primary key autoincrement," +
                "_icon_url text,_mobile text,_name text,_nickname text," +
                "_email text)";
        db.execSQL(s);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.beginTransaction();
        String sql = "DROP TABLE IF EXISTS " + TABLE_NAME;
        db.execSQL(sql);
        String s = "create table if not exists "+ TABLE_NAME+"(_id integer primary key autoincrement," +
                "_icon_url text,_mobile text,_name text,_nickname text," +
                "_email text)";
        close();
        db.setTransactionSuccessful();
        db.endTransaction();
    }
   /* public void dropTable(){
        String sql = "DROP TABLE IF EXISTS " + TABLE_NAME;
        this.getWritableDatabase().execSQL(sql);
        close();
    }*/
    /*public void creatTableAfterDrop(){
        String s = "create table if not exists "+ TABLE_NAME+"(_id integer primary key autoincrement," +
                "_icon_url text,_mobile text,_name text,_nickname text," +
                "_email text)";
        this.getWritableDatabase().execSQL(s);
        close();
    }*/

    public String getTableName(){
        return  TABLE_NAME;
    }

    public void setTableName(String tableName){
        TABLE_NAME = tableName;
    }


}
