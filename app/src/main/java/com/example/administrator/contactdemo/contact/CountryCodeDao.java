package com.example.administrator.contactdemo.contact;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.util.Log;

import com.example.administrator.contactdemo.util.SharePrefrenceManager;
import com.lidroid.xutils.db.sqlite.Selector;
import com.lidroid.xutils.exception.DbException;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class CountryCodeDao extends AsyncTask {
    private static final String SUPPORTCOUNTRYDB = "supportCountry.db";

    private static final String TAG = "CountryCodeDao";
    private Context context;
    private static List<CountryInfo> infoList = null;


    public CountryCodeDao(Context context) {
        this.context = context;
    }

    private void saveCountryCode(List<CountryInfo> infos) {

        try {
            DBManager.getDbUtils().saveAll(infos);
        } catch (DbException e) {
            e.printStackTrace();
            Log.i(TAG, TAG + "saveCountryCode save fail");

        }

    }

    public static List<CountryInfo> getInfos() {

        try {
            if (infoList == null) {
                infoList = DBManager.getDbUtils().findAll(CountryInfo.class);
            }
            return infoList;
        } catch (DbException e) {
            e.printStackTrace();
            Log.i(TAG, TAG + "CountryInfoList get fail");

        }
        return null;
    }

    public static CountryInfo getCodeByISO(String simpleName) {
        try {
            CountryInfo info = DBManager.getDbUtils().findFirst(Selector.from(CountryInfo.class).where("simpleName", "=", simpleName));

            return info;
        } catch (DbException e) {
            e.printStackTrace();
            Log.i(TAG, TAG + "CountryInfo get fail");
        }
        return null;
    }

    /**
     * 判断是否是数据库中包含的国家码
     * @param number
     * @return
     */
    public static String isContainsCountryCode(String number) {
        boolean isAdd=false;
        if(number.startsWith("+")){
            number=number.substring(1);
            isAdd=true;
        }
        List<CountryInfo> infos = getInfos();
        if (infos == null)
            return "";
        for (CountryInfo info : infos) {
            String code=info.getCountryCode();
//            Log.i(TAG,TAG+ "   local country info="+info.toString());
            if (number.startsWith(code)&&number.length()>9) {
//                Log.i(TAG,TAG+"code info==="+code+" number="+number);
                return isAdd?"+"+code:code;
            }
        }
        return "";
    }

    /**
     * 第一次打开app的时候把supportcountryCode保存到本地
     * @param context
     */
    private void copyCountryDb(final Context context) {
        SharePrefrenceManager sharePrefrenceManager = new SharePrefrenceManager(context);
        if (sharePrefrenceManager.getIsFirstLoad()) {
           sharePrefrenceManager.saveIsFirstLoad(false);
            String path = File.separator + "data" + File.separator + "data" + File.separator + context.getPackageName() + File.separator + "databases";// database
            Log.d(TAG, "path==" + path);
            File dir = new File(path);
            if (!dir.exists() || !dir.isDirectory()) {
                dir.mkdirs();
            }
            long start = System.currentTimeMillis();

            InputStream is = null;
            FileOutputStream outs = null;

            try {

                File file = new File(path, SUPPORTCOUNTRYDB);
                if (!file.exists()) {
                    is = context.getAssets().open(SUPPORTCOUNTRYDB);
                    outs = new FileOutputStream(file);
                    int count;
                    byte[] buffer = new byte[1024];
                    while ((count = is.read(buffer)) != -1) {
                        outs.write(buffer, 0, count);
                    }
                }
                Log.i(TAG, "copy db success ==" + SUPPORTCOUNTRYDB);
            } catch (IOException e) {
                Log.i(TAG, "copy db error ==" + SUPPORTCOUNTRYDB);
                e.printStackTrace();
            } finally {
                try {
                    if (is != null) {
                        try {
                            is.close();
                            outs.close();
                        }catch (Exception e){

                        }
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            long now = System.currentTimeMillis() - start;
            Log.d("tangwh", "time==" + now);


        }
    }



    @Override
    protected Object doInBackground(Object[] objects) {
        Log.i(TAG,"start load globalroam_pfingo_model_countryinfo_SupportCountry");
        copyCountryDb(context);
        CountryCodeDbHelper dbHelper = new CountryCodeDbHelper(context);
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from com_globalroam_pfingo_model_countryinfo_SupportCountry", null);
        List<CountryInfo> infos = new ArrayList<>();
        while (cursor.moveToNext()) {
            CountryInfo info = new CountryInfo();
            String code = cursor.getString(cursor.getColumnIndex("countryCode"));
            if (code.length() == 1) {
                info.setCountryCode("00" + code);
            } else {
                info.setCountryCode(cursor.getString(cursor.getColumnIndex("countryCode")));
            }

            info.setCountryName(cursor.getString(cursor.getColumnIndex("countryName")));
            info.setSimpleName(cursor.getString(cursor.getColumnIndex("simpleName")));
            info.setZone(cursor.getString(cursor.getColumnIndex("zone")));

            infos.add(info);
        }
        db.close();
        Collections.sort(infos, new Comparator<CountryInfo>() {
            @Override
            public int compare(CountryInfo c1, CountryInfo c2) {
                return c1.getCountryName().charAt(0) - c2.getCountryName().charAt(0);
            }
        });
        saveCountryCode(infos);
        Log.i(TAG, "finish load globalroam_pfingo_model_countryinfo_SupportCountry");
        return infos;
    }


}
