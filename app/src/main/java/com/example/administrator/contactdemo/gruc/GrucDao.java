package com.example.administrator.contactdemo.gruc;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class GrucDao {
    private Context context;
    private String TAG = "GrucDao";
    private String table_name;
    public GrucDao(Context context,String table_name) {
        this.context = context;
        this.table_name = table_name;
    }

    public void  saveGruc(Gruc gruc){
        GrucDbHelper dbHelper = GrucDbHelper.getInstance(context,table_name);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.beginTransaction();
        db.execSQL("insert into "+ dbHelper.getTableName() +"(_icon_url,_mobile,_name," +
                "_nickname,_email)"+" values(?,?,?,?,?)"
                ,new Object[]{gruc.getIcon_url(),gruc.getMobile(),gruc.getName(),gruc.getNickname(),gruc.getEmail()});
        // database.close();可以不关闭数据库，他里面会缓存一个数据库对象，如果以后还要用就直接用这个缓存的数据库对象。但通过
        // context.openOrCreateDatabase(arg0, arg1, arg2)打开的数据库必须得关闭
        db.setTransactionSuccessful();
        db.endTransaction();
    }
//"create table if not exists "+ TABLE_NAME+"(_id integer primary key autoincrement," +
//            "_icon_url text,_mobile text,_name text,_nickname text," +
//            "_email text)";

    public void saveGrucList(List<Gruc> grucList){
//        Log.i(TAG,"grucList.toString() = "+grucList.toString());
        GrucDbHelper dbHelper = GrucDbHelper.getInstance(context,table_name);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.beginTransaction();
        for (Gruc gruc:grucList){
            db.execSQL("insert into "+ dbHelper.getTableName() +"(_icon_url,_mobile,_name," +
                            "_nickname,_email)"+" values(?,?,?,?,?)"
                    ,new Object[]{gruc.getIcon_url(),gruc.getMobile(),gruc.getName(),gruc.getNickname(),gruc.getEmail()});
//                    ,new String[]{"12","12","12","12","12"});
            // database.close();可以不关闭数据库，他里面会缓存一个数据库对象，如果以后还要用就直接用这个缓存的数据库对象。但通过
            // context.openOrCreateDatabase(arg0, arg1, arg2)打开的数据库必须得关闭

        }
        db.setTransactionSuccessful();
        db.endTransaction();
        Log.i(TAG, "saveGrucList: success");
    }
    public void updateGruc(Gruc gruc) {
        GrucDbHelper dbHelper = GrucDbHelper.getInstance(context,table_name);
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
    public List<Gruc> getGrucList(){
        GrucDbHelper dbHelper = GrucDbHelper.getInstance(context,table_name);
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.query(true, dbHelper.getTableName(), new String[]{"_icon_url", "_mobile", "_name", "_nickname", "_email"},
                null, null, null, null, null, null);
        List<Gruc> grucList = new ArrayList<>();
        while(cursor.moveToNext()){
            int id = cursor.getInt(0);
            String name = cursor.getString(3);
            String _nickname = cursor.getString(4);
            String _mobile = cursor.getString(2);
            String _icon_url = cursor.getString(1);
            String _email = cursor.getString(5);
            Gruc gruc = new Gruc();
            gruc.setId(id+"");
            gruc.setMobile(_mobile);
            gruc.setName(name);
            gruc.setNickname(_nickname);
            gruc.setEmail(_email);
            gruc.setIcon_url(_icon_url);
            grucList.add(gruc);
        }
        cursor.close();

        return grucList;
    }
    public void closeDB(){
        GrucDbHelper.getInstance(context,table_name).close();
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
}
