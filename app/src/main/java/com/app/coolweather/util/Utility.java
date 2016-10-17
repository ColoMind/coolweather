package com.app.coolweather.util;

import android.text.TextUtils;

import com.app.coolweather.model.City;
import com.app.coolweather.model.CoolWeatherDB;
import com.app.coolweather.model.Country;
import com.app.coolweather.model.Province;

/**
 * Created by Administrator on 2016/10/11.
 */

public class Utility {
    public synchronized static boolean handleProvinceResponse(CoolWeatherDB coolWeatherDB,String response){
        if (!TextUtils.isEmpty(response)) {
            String[] allProvinces = response.split(",");
            if ( allProvinces.length > 0){

                for (String p : allProvinces) {
                    Province province = new Province();
                    String[] array = p.split("\\|");
                    province.setProvinceCode(array[0]);
                    province.setProvinceName(array[1]);
                    coolWeatherDB.saveProvince(province);

                }
                return true;
                }
        }

        return false;
    }
    public static boolean handleCityResponse(CoolWeatherDB coolWeatherDB,String response,int provinceId){
        if (!TextUtils.isEmpty(response)) {
            String[] allCities = response.split(",");
            if (allCities.length > 0) {
                for (String ce : allCities) {
                    City city = new City();
                    String[] array = ce.split("\\|");
                    city.setCityCode(array[0]);
                    city.setCityNmae(array[1]);
                    city.setProvinceId(provinceId);
                    coolWeatherDB.saveCity(city);
                }
                return true;
            }
        }
        return false;
    }
    public static boolean handleCountryResponse(CoolWeatherDB coolWeatherDB,String response,int cityId){
        if (!TextUtils.isEmpty(response)) {
            String[] allCountries = response.split(",");
            if (allCountries.length > 0) {
                for (String cr : allCountries) {
                    String[] array = cr.split("\\|");
                    Country country = new Country();
                    country.setCountryCode(array[0]);
                    country.setCountryName(array[1]);
                    country.setCityId(cityId);
                    coolWeatherDB.saveCountry(country);
                }
                return true;
            }
        }
        return false;
    }
}








