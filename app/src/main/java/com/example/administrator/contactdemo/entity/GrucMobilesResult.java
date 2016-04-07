package com.example.administrator.contactdemo.entity;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class GrucMobilesResult {


    /**
     * code : 200
     * matchs : [{"i":null,"m":"8618602811213","u":"chengbin"},{"i":null,"m":"8618628280623","u":"chenfei"}]
     * result : done
     */

    private int code;
    private String result;
    /**
     * i : null
     * m : 8618602811213
     * u : chengbin
     */

    private List<MatchsEntity> matchs;

    public static List<GrucMobilesResult> arrayGrucMobilesResultFromData(String str) {

        Type listType = new TypeToken<ArrayList<GrucMobilesResult>>() {
        }.getType();

        return new Gson().fromJson(str, listType);
    }

    public void setCode(int code) {
        this.code = code;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public void setMatchs(List<MatchsEntity> matchs) {
        this.matchs = matchs;
    }

    public int getCode() {
        return code;
    }

    public String getResult() {
        return result;
    }

    public List<MatchsEntity> getMatchs() {
        return matchs;
    }

    public static class MatchsEntity {
        private String i;
        private String m;
        private String u;

        public static List<MatchsEntity> arrayMatchsEntityFromData(String str) {

            Type listType = new TypeToken<ArrayList<MatchsEntity>>() {
            }.getType();

            return new Gson().fromJson(str, listType);
        }

        public void setI(String i) {
            this.i = i;
        }

        public void setM(String m) {
            this.m = m;
        }

        public void setU(String u) {
            this.u = u;
        }

        public String getI() {
            return i;
        }

        public String getM() {
            return m;
        }

        public String getU() {
            return u;
        }
    }
}
