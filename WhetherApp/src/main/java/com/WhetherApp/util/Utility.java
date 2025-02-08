package com.WhetherApp.util;

public class Utility {

    private final static String API_KEY = "YOUR_API_KEY";

    public static String findLatitideAndLongitude(String cityName, String stateCode){
        stateCode = stateCode.toUpperCase();
        return "https://api.openweathermap.org/geo/1.0/direct?q="+cityName+","+stateCode+",IN&limit=3&appid="+API_KEY;
    }

    public static String findTempcutre(float latitude, int longititude){
        return "https://api.openweathermap.org/data/2.5/weather?lat="+latitude+"&lon="+longititude+"&appid="+API_KEY;
    }
}



