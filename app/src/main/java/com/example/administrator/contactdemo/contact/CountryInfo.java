package com.example.administrator.contactdemo.contact;


import com.lidroid.xutils.db.annotation.Column;
import com.lidroid.xutils.db.annotation.Id;
import com.lidroid.xutils.db.annotation.Table;

@Table(name="country_code")
public class CountryInfo {
    @Id
    private int id;
    @Column(column = "countryCode")
    private String countryCode;
    @Column(column = "countryName")
    private String countryName;
    @Column(column = "simpleName")
    private String simpleName;
    @Column(column = "zone")
    private String zone;


    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public void setZone(String zone) {
        this.zone = zone;
    }

    public void setSimpleName(String simpleName) {
        this.simpleName = simpleName;
    }

    public String getSimpleName() {
        return simpleName;
    }

    public String getZone() {
        return zone;
    }

    @Override
    public String toString() {
        return "CountryInfo{" +
                "id=" + id +
                ", countryCode='" + countryCode + '\'' +
                ", countryName='" + countryName + '\'' +
                ", simpleName='" + simpleName + '\'' +
                ", zone='" + zone + '\'' +
                '}';
    }
}

