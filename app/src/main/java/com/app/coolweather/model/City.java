package com.app.coolweather.model;

/**
 * Created by Administrator on 2016/10/3.
 */

public class City {
    private int id;
    private String cityNmae;
    private String cityCode;
    private int provinceId;
    public int getId(){
        return id;
    }
    public void setId(int id){
        this.id = id;
    }
    public String getCityNmae(){
        return cityNmae;
    }
    public void setCityNmae(String cityNmae){
        this.cityNmae = cityNmae;
    }
    public String getCityCode(){
        return  cityCode;
    }
    public void setCityCode(String cityCode){
        this.cityCode = cityCode;
    }

    public int getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(int provinceId) {
        this.provinceId = provinceId;
    }
}
