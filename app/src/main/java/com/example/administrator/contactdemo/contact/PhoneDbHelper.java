package com.example.administrator.contactdemo.contact;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.text.TextUtils;

public class PhoneDbHelper extends SQLiteOpenHelper{
    private static final String name = "demoDB";
    private String TABLE_NAME = "phones";
    private static final int version = 1;
    private static PhoneDbHelper dbHelper;
    public static synchronized PhoneDbHelper getInstance(Context context,String table_name){
        if (dbHelper == null){
            dbHelper = new PhoneDbHelper(context,table_name);
        }
        return dbHelper;
    }
    private PhoneDbHelper(Context context,String table_name) {
        super(context, name, null, version);
        if (!TextUtils.isEmpty(table_name)){
            this.TABLE_NAME = table_name;
        }
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String s = "create table if not exists "+ TABLE_NAME+"(_id integer primary key autoincrement," +
                "_type integer,_number text,_photo_thumbnail text, _contactId text," +
                " _contactName text, _contactVersion text, _countryCode text,_grucType,_icon_url,_mobile,_name,_nickname,_email)";
        db.execSQL(s);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.beginTransaction();
        String sql = "DROP TABLE IF EXISTS " + TABLE_NAME;
        db.execSQL(sql);
        /*String s = "create table if not exists "+ TABLE_NAME+"(_id integer primary key autoincrement," +
                "_type integer,_number text,_photo_thumbnail text, _contactId text," +
                " _contactName text, _contactVersion text, _countryCode text,_grucType,_icon_url,_mobile,_name,_nickname,_email)";
        db.execSQL(s);*/
//        onCreate(db);
        db.setTransactionSuccessful();
        db.endTransaction();
    }
    public void creatTableAfterDrop(){
        String s = "create table if not exists "+ TABLE_NAME+"(_id integer primary key autoincrement," +
                "_type integer,_number text,_photo_thumbnail text, _contactId text," +
                " _contactName text, _contactVersion text, _countryCode text,_grucType,_icon_url,_mobile,_name,_nickname,_email)";
        this.getWritableDatabase().execSQL(s);
    }
    public String getTableName(){
        return  TABLE_NAME;
    }
    public void setTableName(String tableName){
        TABLE_NAME = tableName;
    }

}
