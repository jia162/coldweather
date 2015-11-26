package com.coldweather.app.util;

import android.text.TextUtils;

import com.coldweather.app.db.ColdWeatherDB;
import com.coldweather.app.model.City;
import com.coldweather.app.model.County;
import com.coldweather.app.model.Province;

public class Utility {
	public synchronized static boolean handleProvincesResponse(ColdWeatherDB coldWeatherDB, 
			String response) {
		if (!TextUtils.isEmpty(response)) {
			String[] allProvinces = response.split(",");
			if (allProvinces != null && allProvinces.length > 0) {
				for (String p : allProvinces) {
					String[] array = p.split("\\|");
					Province province = new Province();
					province.setProvinceCode(array[0]);
					province.setProvinceName(array[1]);
					coldWeatherDB.saveProvince(province);
				}
				return true;
			}
		}
		return false;
	}
	
	public synchronized static boolean handleCitiesResponse(ColdWeatherDB coldWeatherDB,
			String response, int provinceID) {
		if (!TextUtils.isEmpty(response)) {
			String[] allCities = response.split(",");
			if (allCities != null && allCities.length > 0) {
				for (String c : allCities) {
					String[] array = c.split("\\|");
					City city = new City();
					city.setCityCode(array[0]);
					city.setCityName(array[1]);
					city.setProvinceId(provinceID);
					coldWeatherDB.saveCity(city);
				}
				return true;
			}
		}
		return false;
	}
	
	public synchronized static boolean handleCountiesResponse(ColdWeatherDB coldWeatherDB, 
			String response, int cityID) {
		if (!TextUtils.isEmpty(response)) {
			String[] allCounties = response.split(",");
			if (allCounties != null && allCounties.length > 0) {
				for (String c : allCounties) {
					String[] array = c.split("\\|");
					County county = new County();
					county.setCountyCode(array[0]);
					county.setCountyName(array[1]);
					county.setCityId(cityID);
					coldWeatherDB.saveCounty(county);
				}
				return true;
			}
		}
		return false;
	}
}


