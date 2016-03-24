package com.example.administrator.contactdemo.entity;

public class UserDate {

    /**
     * username : d1_m1_u1
     * password : 123456
     * domain : d1_m1.gnum.com
     */

    private String username;
    private String password;
    private String domain;

    public UserDate() {
    }

    public UserDate(String username, String password, String domain) {
        this.username = username;
        this.password = password;
        this.domain = domain;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }
}
