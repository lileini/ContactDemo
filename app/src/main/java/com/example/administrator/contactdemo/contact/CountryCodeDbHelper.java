package com.example.administrator.contactdemo.contact;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class CountryCodeDbHelper extends SQLiteOpenHelper{
    private static final String SUPPORTCOUNTRYDB = "supportCountry.db";

    private static final String TAG = "CountryCodeDao";
    private static final int VERSION = 1;

    public CountryCodeDbHelper(Context context) {
        super(context, SUPPORTCOUNTRYDB, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
