package com.example.administrator.contactdemo.contact;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import java.util.List;

public class ContactDao {
    private final ContactDbHelper dbHelper;

    public ContactDao(Context context) {
        dbHelper = new ContactDbHelper(context);
    }


    public void savePhone(Phone phone){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.beginTransaction();
        db.execSQL("insert into "+ dbHelper.getTableName() +"(_type,_number,_photo_thumbnail,_contactId," +
                "_contactName,_contactVersion,_countryCode)"+" value(?,?,?,?,?,?,?)"
                ,new Object[]{phone.getType(),phone.getNumber(),phone.getPhoto_thumbnail(),phone.getContactId(),
                phone.getContactName(),phone.getContactVersion(),phone.getCountryCode()});
        // database.close();可以不关闭数据库，他里面会缓存一个数据库对象，如果以后还要用就直接用这个缓存的数据库对象。但通过
        // context.openOrCreateDatabase(arg0, arg1, arg2)打开的数据库必须得关闭
        db.setTransactionSuccessful();
        db.endTransaction();
    }
    public void updatePhone(Phone phone) {
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("_type", phone.getType());
        contentValues.put("_number", phone.getNumber());
        contentValues.put("_photo_thumbnail", phone.getPhoto_thumbnail());
        contentValues.put("_contactId", phone.getContactId());
        contentValues.put("_contactName", phone.getContactName());
        contentValues.put("_contactVersion", phone.getContactVersion());
        contentValues.put("_countryCode", phone.getCountryCode());
        database.update(dbHelper.getTableName(), contentValues, "_id=?",
                new String[]{String.valueOf(phone.getId())});
    }
    public void deletePhone(Phone phone){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.beginTransaction();
        db.execSQL("delete");
        // database.close();可以不关闭数据库，他里面会缓存一个数据库对象，如果以后还要用就直接用这个缓存的数据库对象。但通过
        // context.openOrCreateDatabase(arg0, arg1, arg2)打开的数据库必须得关闭
        db.setTransactionSuccessful();
        db.endTransaction();
    }
    public List<Phone> queryPhone(){
        return null;
    }
}
