package com.example.administrator.contactdemo.contact;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ContactDbHelper extends SQLiteOpenHelper{
    private static final String name = "demoDB";
    private static final String TABLE_NAME = "phones";
    private static final int version = 1;

    public ContactDbHelper(Context context) {
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
        String s = "create table if not exists"+ TABLE_NAME+"(_id integer primary key autoincrement," +
                "_type integer,_number text,_photo_thumbnail text, _contactId text," +
                " _contactName text, _contactVersion text, _countryCode text)";
        db.execSQL(s);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "DROP TABLE IF EXISTS " + TABLE_NAME;
        db.execSQL(sql);
        onCreate(db);
    }
    public String getTableName(){
        return  TABLE_NAME;
    }

}
