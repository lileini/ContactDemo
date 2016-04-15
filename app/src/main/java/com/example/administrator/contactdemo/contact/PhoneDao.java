package com.example.administrator.contactdemo.contact;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import java.util.List;

public class PhoneDao {
    private Context context;
    public PhoneDao(Context context) {
        this.context = context;
    }

    public void savePhone(Phone phone){
        PhoneDbHelper dbHelper = new PhoneDbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.beginTransaction();
        db.execSQL("insert into "+ dbHelper.getTableName() +"(_type,_number,_photo_thumbnail,_contactId," +
                "_contactName,_contactVersion,_countryCode,_grucType,_gruc_photo,_gruc_name)"+" value(?,?,?,?,?,?,?,?,?,?)"
                ,new Object[]{phone.getType(),phone.getNumber(),phone.getPhoto_thumbnail(),phone.getContactId(),
                phone.getContactName(),phone.getContactVersion(),phone.getCountryCode(),phone.getGrucType(),phone.getGruc_photo(),phone.getGruc_name()});
        // database.close();可以不关闭数据库，他里面会缓存一个数据库对象，如果以后还要用就直接用这个缓存的数据库对象。但通过
        // context.openOrCreateDatabase(arg0, arg1, arg2)打开的数据库必须得关闭
        db.setTransactionSuccessful();
        db.endTransaction();
    }

    public void savePhones(List<Phone> phones){
        PhoneDbHelper dbHelper = new PhoneDbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.beginTransaction();
        for (Phone phone:phones){
            db.execSQL("insert into "+ dbHelper.getTableName() +"(_type,_number,_photo_thumbnail,_contactId," +
                    "_contactName,_contactVersion,_countryCode,_grucType,_gruc_photo,_gruc_name)"+" values(?,?,?,?,?,?,?,?,?,?)"
                    ,new Object[]{phone.getType(), phone.getNumber(),phone.getPhoto_thumbnail(), phone.getContactId(),
                    phone.getContactName(),phone.getContactVersion(), phone.getCountryCode(),phone.getGrucType(),phone.getGruc_photo(),phone.getGruc_name()});
            // database.close();可以不关闭数据库，他里面会缓存一个数据库对象，如果以后还要用就直接用这个缓存的数据库对象。但通过
            // context.openOrCreateDatabase(arg0, arg1, arg2)打开的数据库必须得关闭

        }
        db.setTransactionSuccessful();
        db.endTransaction();
    }
    public void updatePhone(Phone phone) {
        PhoneDbHelper dbHelper = new PhoneDbHelper(context);
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("_type", phone.getType());
        contentValues.put("_number", phone.getNumber());
        contentValues.put("_photo_thumbnail", phone.getPhoto_thumbnail());
        contentValues.put("_contactId", phone.getContactId());
        contentValues.put("_contactName", phone.getContactName());
        contentValues.put("_contactVersion", phone.getContactVersion());
        contentValues.put("_countryCode", phone.getCountryCode());
        contentValues.put("_grucType", phone.getGrucType());
        contentValues.put("_gruc_photo", phone.getGruc_photo());
        contentValues.put("_gruc_name", phone.getGruc_name());
        database.update(dbHelper.getTableName(), contentValues, "_id=?",
                new String[]{String.valueOf(phone.getId())});
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
    public List<Phone> queryPhone(){
        return null;
    }
}
