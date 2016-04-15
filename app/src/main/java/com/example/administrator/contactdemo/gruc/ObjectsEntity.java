package com.example.administrator.contactdemo.gruc;

public class ObjectsEntity {

    /**
     * domain_id : DOMAINc2b718fe-f73f-11e5-870a-fa163e17f4ed
     * email : shaohua@globalroam.com
     * enable : true
     * icon_url : null
     * id : SUBUSR4d006d98-f746-11e5-a847-fa163e17f4ed
     * mobile : 8618708157378
     * name : shaohua
     * nickname : null
     */

    private String domain_id;
    private String email;
    private boolean enable;
    private String icon_url;
    private String id;
    private String mobile;
    private String name;
    private String nickname;



    public void setDomain_id(String domain_id) {
        this.domain_id = domain_id;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    public void setIcon_url(String icon_url) {
        this.icon_url = icon_url;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getDomain_id() {
        return domain_id;
    }

    public String getEmail() {
        return email;
    }

    public boolean isEnable() {
        return enable;
    }

    public String getIcon_url() {
        return icon_url;
    }

    public String getId() {
        return id;
    }

    public String getMobile() {
        return mobile;
    }

    public String getName() {
        return name;
    }

    public String getNickname() {
        return nickname;
    }

    @Override
    public String toString() {
        return "ObjectsEntity{" +
                "domain_id='" + domain_id + '\'' +
                ", email='" + email + '\'' +
                ", enable=" + enable +
                ", icon_url='" + icon_url + '\'' +
                ", id='" + id + '\'' +
                ", mobile='" + mobile + '\'' +
                ", name='" + name + '\'' +
                ", nickname='" + nickname + '\'' +
                '}';
    }
}
