package com.danish.weatherapp;

import android.text.Html;

import org.json.JSONException;
import org.json.JSONObject;

public class weatherData {
    private String mTemperature, micon, mcity, mWeatherType;
    private int mCondition;

    public static weatherData fromJson(JSONObject jsonObject) {
        try {
            weatherData weatherD = new weatherData();
            weatherD.mcity = jsonObject.getString("name");
            weatherD.mCondition = jsonObject.getJSONArray("weather").getJSONObject(0).getInt("id");
            weatherD.mWeatherType = jsonObject.getJSONArray("weather").getJSONObject(0).getString("main");
            weatherD.micon = updateWeatherIcon(weatherD.mCondition);
            double tempResult = jsonObject.getJSONObject("main").getDouble("temp") - 273.15;
            int roundValue = (int) Math.rint(tempResult);
            weatherD.mTemperature = Integer.toString(roundValue);
            return weatherD;

        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }

    }

    private static String updateWeatherIcon(int condition)
    {
        if (condition >= 0 && condition <= 300) {
            return "thunderstrom1";
        }
        if(condition==903)
        {
            return "snow1";
        }
        if(condition==904)
        {
            return "sunny";

        }
        if(condition>=905 && condition<=1000)
        {
            return "thunderstrom2";
        }
        return "dunno";
    }

    public String getmTemperature()
    {
        return mTemperature+"°C";
    }
    public String getMicon()
    {
        return micon;
    }
    public String getMcity()
    {
        return mcity;
    }
    public String getmWeatherType()
    {
        return mWeatherType;
    }


}
