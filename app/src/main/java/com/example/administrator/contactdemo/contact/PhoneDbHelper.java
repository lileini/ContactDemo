package com.example.administrator.contactdemo.contact;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class PhoneDbHelper extends SQLiteOpenHelper{
    private static final String name = "demoDB";
    private String TABLE_NAME = "phones";
    private static final int version = 1;

    public PhoneDbHelper(Context context) {
        super(context, name, null, version);
    }

//    private String type;
//    private String number;
//    private String photo_thumbnail;
//    private String contactId;
//    private String contactName;
//    private String contactVersion;
//    private String countryCode = "";
    @Override
    public void onCreate(SQLiteDatabase db) {
        String s = "create table if not exists "+ TABLE_NAME+"(_id integer primary key autoincrement," +
                "_type integer,_number text,_photo_thumbnail text, _contactId text," +
                " _contactName text, _contactVersion text, _countryCode text,_grucType,_gruc_photo,_gruc_name)";
        db.execSQL(s);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.beginTransaction();
        String sql = "DROP TABLE IF EXISTS " + TABLE_NAME;
        db.execSQL(sql);
//        db.execSQL(s);
        onCreate(db);
        db.setTransactionSuccessful();
        db.endTransaction();
    }
    public String getTableName(){
        return  TABLE_NAME;
    }
    public void setTableName(String tableName){
        TABLE_NAME = tableName;
    }

}
