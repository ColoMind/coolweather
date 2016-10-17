package com.app.coolweather.model;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.app.coolweather.R;
import com.app.coolweather.db.CoolWeatherOpenHelper;
import com.app.coolweather.util.HttpCallbackListener;
import com.app.coolweather.util.HttpUtil;
import com.app.coolweather.util.Utility;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/10/3.
 */

public class CoolWeatherDB {
    public static final String DB_NAME = "cool_weather";

    public static final int VERSION = 1;
    public static CoolWeatherDB coolWeatherDB;
    public static SQLiteDatabase db;

    private CoolWeatherDB(Context context){
        CoolWeatherOpenHelper dbhelper = new CoolWeatherOpenHelper(context,DB_NAME,null,VERSION);
        db = dbhelper.getWritableDatabase();
    }
    public synchronized static CoolWeatherDB getInstance(Context context){
        if(coolWeatherDB == null){
            coolWeatherDB = new CoolWeatherDB(context);
        }
        return coolWeatherDB;
    }
    public void saveProvince(Province province){
        if (province != null){
            ContentValues values = new ContentValues();
            values.put("province_name",province.getProvinceName());
            values.put("province_code",province.getProvinceCode());
            db.insert("Province",null,values);
        }
    }
    public List<Province> loadProvince(){
        List<Province> list = new ArrayList<Province>();
        Cursor cursor  = db.query("Province",null,null,null,null,null,null);
        if (cursor.moveToNext()){
            do {
                Province province = new Province();
                province.setId(cursor.getInt(cursor.getColumnIndex("id")));
                province.setProvinceName(cursor.getString(cursor.getColumnIndex("province_name")));
                province.setProvinceCode(cursor.getString(cursor.getColumnIndex("province_code")));
                list.add(province);
            }while (cursor.moveToNext());
        }
        if (cursor != null){
            cursor.close();
        }
        return list;
    }
    public void saveCity(City city){
        if (city != null) {
            ContentValues values = new ContentValues();
            values.put("city_name", city.getCityNmae());
            values.put("city_code", city.getCityCode());
            values.put("province_id", city.getProvinceId());
            db.insert("City", null, values);
        }
    }
    public List<City> loadCity(int provinceId){
        List<City> cityList = new ArrayList<City>();
        Cursor cursor = db.query("City",null,"province_id = ?",new String[] {String.valueOf(provinceId) },null,null,null);
        if (cursor.moveToNext()){
            do {
                City city = new City();
                city.setId(cursor.getInt(cursor.getColumnIndex("id")));
                city.setCityNmae(cursor.getString(cursor.getColumnIndex("city_name")));
                city.setCityCode(cursor.getString(cursor.getColumnIndex("city_code")));
                city.setProvinceId(provinceId);
                cityList.add(city);
            }while (cursor.moveToNext());
        }
        if (cursor != null){
            cursor.close();
        }
        return  cityList;
    }
    public void saveCountry(Country country){
        if (country != null) {
            ContentValues values = new ContentValues();
            values.put("country_name",country.getCountryName());
            values.put("country_code",country.getCountryCode());
            values.put("city_id",country.getCityId());
            db.insert("Country",null,values);
        }
    }
    public List<Country> loadCountry(int cityId){
        Country country = new Country();
        List<Country> countryList = new ArrayList<Country>();
        Cursor cursor = db.query("Country",null,"cityId = ?",new String[]{String.valueOf(cityId)},null,null,null);
        if ( cursor.moveToNext()){
            do {
                country.setId(cursor.getInt(cursor.getColumnIndex("id")));
                country.setCountryName(cursor.getString(cursor.getColumnIndex("country_name")));
                country.setCountryCode(cursor.getString(cursor.getColumnIndex("country_code")));
                country.setCityId(cityId);
                countryList.add(country);
            }while (cursor.moveToNext());
            if (cursor != null){
                cursor.close();
            }
        }
        return countryList;
    }
}
















