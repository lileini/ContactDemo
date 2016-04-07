package com.example.administrator.contactdemo.entity;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class GrucUser {
    private String m;
    private String u;

    public List<GrucUser> arrayMatchsEntityFromData(String str) {

        Type listType = new TypeToken<ArrayList<GrucUser>>() {
        }.getType();

        return new Gson().fromJson(str, listType);
    }

    public void setM(String m) {
        this.m = m;
    }

    public void setU(String u) {
        this.u = u;
    }

    public String getM() {
        return m;
    }

    public String getU() {
        return u;
    }
}
