package com.example.administrator.contactdemo.contact;

import android.app.LoaderManager;
import android.content.Context;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;

import com.example.administrator.contactdemo.entity.Const;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class PhonesLoader implements LoaderManager.LoaderCallbacks<Cursor> {
    private final String[] CONTACTS_SUMMARY_PROJECTION = new String[]{
            ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,
            ContactsContract.CommonDataKinds.Phone.NUMBER,
            ContactsContract.CommonDataKinds.Phone.CONTACT_ID,
            ContactsContract.CommonDataKinds.Phone.PHOTO_THUMBNAIL_URI,
            ContactsContract.CommonDataKinds.Phone.TYPE,
            ContactsContract.RawContacts.VERSION
    };

    private final int PHONE_NAME = 0;
    private final int PHONE_NUMBER = 1;
    private final int PHONE_ID = 2;
    private final int PHONE_PHOTO_ID = 3;
    private final int PHONE_TYPE = 4;
    private final int CONTACT_VERSION=5;


    private Context context;
    private final String TAG="PhonesLoader";

    private static PhonesLoader instance;

    private PhonesLoader(Context context){
        this.context=context;
    }
    public static PhonesLoader getInstance(Context context){
        if(instance==null){
            instance = new PhonesLoader(context);
        }
        return instance;
    }


    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        Log.i(TAG, "MyLoaderListener---------->onCreateLoader");
        Uri uri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;//content://com.android.contacts/data/phones  从手机查询取得联系人
        return new CursorLoader(context,uri,CONTACTS_SUMMARY_PROJECTION,null,null,null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        Log.d(TAG, "MyLoaderListener---------->onLoadFinished");
        queryByCursor(data);
        addPhonesMap();
        Intent i = new Intent();
        i.setAction(Const.CONTACT_LOAD_FINISHED);
        Log.i(TAG, "contacts loaded finish and had sent broadcast");
        //记载完成发送广播
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            context.sendBroadcast(i);
        } else {
            context.sendOrderedBroadcast(i, null);
        }
    }

    private void addPhonesMap() {
        if (Const.phoneList != null){
            Const.phoneMap = new HashMap<>(Const.phoneList.size());
            for (Phone phone:Const.phoneList){
                Const.phoneMap.put(phone.getCountryCode()+phone.getNumber(),phone);
            }
        }
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        Log.d(TAG, "MyLoaderListener---------->onLoaderReset");

    }


    public void queryByCursor(Cursor cursor) {

        Const.phoneList = queryPhone(cursor);
        sortPhoneList(Const.phoneList);
//        List<Contact> contactList = createContactList(phoneList);

        Log.i(TAG, TAG + "phoneList.Size= " + Const.phoneList.size());
        Collections.sort(Const.phoneList);
    }

    /**
     * 查询号码
     * @param cursor
     * @return
     */
    public List<Phone> queryPhone(Cursor cursor) {
        long startTime = System.currentTimeMillis();
        List<Phone> phoneList = new ArrayList<>();
        if(cursor!=null){
            cursor.moveToFirst();
            HashSet hs = new HashSet();
            while (!cursor.isAfterLast()) {

                String number = cursor.getString(PHONE_NUMBER).replace(" ", "");
                String contactName = cursor.getString(PHONE_NAME);

                if(hs.add(number+contactName)){
                    String photo_thumbnail = cursor.getString(PHONE_PHOTO_ID);
                    String type = cursor.getString(PHONE_TYPE);
                    String contactId = cursor.getString(PHONE_ID);
                    String contactVsersion=cursor.getString(CONTACT_VERSION);
                    Phone phone = new Phone(type, number, photo_thumbnail, contactId, contactName);
                    phone.setLetter();
                    phone.setContactVersion(contactVsersion);
                    phoneList.add(phone);

                }

                cursor.moveToNext();
            }
            Log.i(TAG, "queryPhone  query time ==" + (System.currentTimeMillis() - startTime));
        }

        return phoneList;
    }



    /**
     * 按照contactId排序
     * @param phoneList
     */
    public void sortPhoneList(List<Phone> phoneList) {
//        long startTime = System.currentTimeMillis();
        Collections.sort(phoneList, new Comparator<Phone>() {
            @Override
            public int compare(Phone one, Phone two) {
                long oneId = Long.parseLong(one.getContactId());
                long twoId = Long.parseLong(two.getContactId());
                return (int) (oneId - twoId);
            }
        });
    }


    public static void contactHasChanged(List<Phone> oldPhoneList, List<Phone> newPhoneList) {

        //查找更改的数据
        boolean notFind=true;
        for (int i = 0; i < oldPhoneList.size(); i++) {
            for (int j = 0; j < newPhoneList.size(); j++) {
                if (oldPhoneList.get(i).getContactId().equals(newPhoneList.get(j).getContactId())) {
                    Log.i("contact", "contact changed-----同联系人---contactList-" + oldPhoneList.get(i).toString());
                    notFind=false;
                    if (!oldPhoneList.get(i).getContactVersion().equals(newPhoneList.get(j).getContactVersion())) {
                        Log.i("contact","contact changed-----"+oldPhoneList.get(i).toString());

                        newPhoneList.remove(j);
                        oldPhoneList.remove(i);
                        break;
                    } else {
                        newPhoneList.remove(j);
                        break;
                    }
                }else{
                    notFind=true;
                }
            }

            if (notFind) {
                Log.i("contact","contact changed----"+oldPhoneList.get(i).toString());
            }
        }
    }
}
