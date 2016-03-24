package com.example.administrator.contactdemo.entity;

import com.example.administrator.contactdemo.contact.Phone;

import java.util.HashMap;
import java.util.List;

public class Const {
    public enum PhoneLoadState{
        Loading,Loaded
    }
    public static final String CONTACT_LOAD_FINISHED  ="CONTACT_LOAD_FINISHED";
    public static List<Phone> phoneList;

    public static HashMap<String,Phone> phoneMap;

    public static final String BASE_URL= "http://61.8.195.42/";
    public static final String ACCESS_TOKEN_URL= BASE_URL+"authuser";
    public static final String CONFIRM_GRUC_MOBILE_URL = BASE_URL+"api/op/matchmobile";

    /**
     * 表示查询全部
     */
    public static final int INTENT_FLAG0= 0;
    public static final int INTENT_FLAG1= 1;
    public static final int INTENT_FLAG2= 2;
}