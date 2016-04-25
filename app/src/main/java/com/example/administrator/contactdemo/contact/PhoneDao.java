package com.example.administrator.contactdemo.contact;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.lidroid.xutils.util.LogUtils;

import java.util.ArrayList;
import java.util.List;

public class PhoneDao {
    private Context context;
    private String table_name;
    public PhoneDao(Context context,String table_name) {
        this.context = context;
        this.table_name = table_name;
    }
    public void savePhone(Phone phone){
        PhoneDbHelper dbHelper = PhoneDbHelper.getInstance(context,table_name);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.beginTransaction();
        db.execSQL("insert into "+ dbHelper.getTableName() +"(_type,_number,_photo_thumbnail,_contactId," +
                "_contactName,_contactVersion,_countryCode,_grucType,_icon_url,_mobile,_name,_nickname,_email)"
                +" values(?,?,?,?,?,?,?,?,?,?,?,?,?)"
                ,new Object[]{phone.getType(),phone.getNumber(),phone.getPhoto_thumbnail(),phone.getContactId(),
                phone.getContactName(),phone.getContactVersion(),phone.getCountryCode(),
                        phone.getGrucType(),phone.getIcon_url(),phone.getMobile(),phone.getName(),
                        phone.getNickname(),phone.getEmail()});
        // database.close();可以不关闭数据库，他里面会缓存一个数据库对象，如果以后还要用就直接用这个缓存的数据库对象。但通过
        // context.openOrCreateDatabase(arg0, arg1, arg2)打开的数据库必须得关闭
        db.setTransactionSuccessful();
        db.endTransaction();
    }

    public void savePhones(List<Phone> phones){
        LogUtils.i(" savePhones phones="+phones);
        PhoneDbHelper dbHelper = PhoneDbHelper.getInstance(context,table_name);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.beginTransaction();
        for (Phone phone:phones){
            db.execSQL("insert into "+ dbHelper.getTableName() +"(_type,_number,_photo_thumbnail,_contactId," +
                    "_contactName,_contactVersion,_countryCode,_grucType,_icon_url,_mobile,_name,_nickname,_email)"+" values(?,?,?,?,?,?,?,?,?,?,?,?,?)"
                    ,new Object[]{phone.getType(), phone.getNumber(),phone.getPhoto_thumbnail(), phone.getContactId(),
                    phone.getContactName(),phone.getContactVersion(), phone.getCountryCode(),
                            phone.getGrucType(),phone.getIcon_url(),phone.getMobile(),phone.getName(),
                            phone.getNickname(),phone.getEmail()});
            // database.close();可以不关闭数据库，他里面会缓存一个数据库对象，如果以后还要用就直接用这个缓存的数据库对象。但通过
            // context.openOrCreateDatabase(arg0, arg1, arg2)打开的数据库必须得关闭

        }
        db.setTransactionSuccessful();
        db.endTransaction();
    }
    public void updatePhone(Phone phone) {
        PhoneDbHelper dbHelper = PhoneDbHelper.getInstance(context,table_name);
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
        contentValues.put("_icon_url", phone.getIcon_url());
        contentValues.put("_mobile", phone.getMobile());
        contentValues.put("_name", phone.getName());
        contentValues.put("_nickname", phone.getNickname());
        contentValues.put("_email", phone.getEmail());
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

    public List<Phone> getPhoneList(){
        PhoneDbHelper dbHelper = PhoneDbHelper.getInstance(context,table_name);
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        db.beginTransaction();
        Cursor cursor = db.query(true, dbHelper.getTableName(), new String[]{"_type", "_number", "_photo_thumbnail",
                "_contactId", "_contactName","_contactVersion", "_countryCode", "_grucType",
                "_icon_url", "_mobile", "_name", "_nickname", "_email"}, null, null, null, null, null, null);
        List<Phone> phoneList = new ArrayList<>();
        while (cursor.moveToNext()){
            long id = cursor.getInt(0);
            String type = cursor.getString(1);
            String number = cursor.getString(2);
            String photo_thumbnail = cursor.getString(3);
            String contactId = cursor.getString(4);
            String contactName = cursor.getString(5);
            String contactVersion = cursor.getString(6);
            String countryCode = cursor.getString(7);
            int grucType = cursor.getInt(8);
            String icon_url = cursor.getString(9);
            String mobile = cursor.getString(10);
            String name = cursor.getString(11);
            String nickname = cursor.getString(12);
            String email = cursor.getString(13);
            Phone phone = new Phone(id,type,number,photo_thumbnail,contactId,contactName,contactVersion,
                    countryCode,grucType,icon_url,mobile,name,nickname,email);
            phoneList.add(phone);
        }
        db.setTransactionSuccessful();
        db.endTransaction();
        return phoneList;
    }
}
