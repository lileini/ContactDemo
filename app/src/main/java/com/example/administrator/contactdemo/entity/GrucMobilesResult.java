package com.example.administrator.contactdemo.entity;

import java.util.List;

public class GrucMobilesResult {

    /**
     * matchs : ["200","220"]
     * result : done
     */

    private String result;
    private List<String> matchs;

    public void setResult(String result) {
        this.result = result;
    }

    public void setMatchs(List<String> matchs) {
        this.matchs = matchs;
    }

    public String getResult() {
        return result;
    }

    public List<String> getMatchs() {
        return matchs;
    }
}
