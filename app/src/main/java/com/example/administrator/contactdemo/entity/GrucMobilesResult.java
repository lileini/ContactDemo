package com.example.administrator.contactdemo.entity;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class GrucMobilesResult {


    /**
     * code : 200
     * matchs : [{"domain_id":"DOMAINc2b718fe-f73f-11e5-870a-fa163e17f4ed","email":"chengbin@globalroam.com","i":null,"icon_url":null,"id":"SUBUSR44535098-f746-11e5-9aef-fa163e17f4ed","m":"8618602811213","mobile":"8618602811213","name":"chengbin","nickname":null,"setting_json":null,"u":"chengbin","url":null}]
     * result : done
     */

    private int code;
    private String result;
    /**
     * domain_id : DOMAINc2b718fe-f73f-11e5-870a-fa163e17f4ed
     * email : chengbin@globalroam.com
     * i : null
     * icon_url : null
     * id : SUBUSR44535098-f746-11e5-9aef-fa163e17f4ed
     * m : 8618602811213
     * mobile : 8618602811213
     * name : chengbin
     * nickname : null
     * setting_json : null
     * u : chengbin
     * url : null
     */

    private List<MatchsEntity> matchs;

    public static List<GrucMobilesResult> arrayGrucMobilesResultFromData(String str) {

        Type listType = new TypeToken<ArrayList<GrucMobilesResult>>() {
        }.getType();

        return new Gson().fromJson(str, listType);
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public List<MatchsEntity> getMatchs() {
        return matchs;
    }

    public void setMatchs(List<MatchsEntity> matchs) {
        this.matchs = matchs;
    }

    public static class MatchsEntity {
        private String domain_id;
        private String email;
        private String i;
        private String icon_url;
        private String id;
        private String m;
        private String mobile;
        private String name;
        private String nickname;
        private String setting_json;
        private String u;
        private String url;

        public static List<MatchsEntity> arrayMatchsEntityFromData(String str) {

            Type listType = new TypeToken<ArrayList<MatchsEntity>>() {
            }.getType();

            return new Gson().fromJson(str, listType);
        }

        public String getDomain_id() {
            return domain_id;
        }

        public void setDomain_id(String domain_id) {
            this.domain_id = domain_id;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getI() {
            return i;
        }

        public void setI(String i) {
            this.i = i;
        }

        public String getIcon_url() {
            return icon_url;
        }

        public void setIcon_url(String icon_url) {
            this.icon_url = icon_url;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getM() {
            return m;
        }

        public void setM(String m) {
            this.m = m;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public Object getSetting_json() {
            return setting_json;
        }

        public void setSetting_json(String setting_json) {
            this.setting_json = setting_json;
        }

        public String getU() {
            return u;
        }

        public void setU(String u) {
            this.u = u;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }
}
