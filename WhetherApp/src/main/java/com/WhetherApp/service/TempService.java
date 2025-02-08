package com.WhetherApp.service;

import com.WhetherApp.res.ApiRes;
import com.WhetherApp.util.Utility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.*;

import static com.WhetherApp.util.Utility.findLatitideAndLongitude;
import static com.WhetherApp.util.Utility.findTempcutre;

@Service
public class TempService {

    @Autowired
    private RestTemplate restTemplate;

    public ApiRes getTempacture(String stateCode, String cityName) {
        try {
            String s = findLatitideAndLongitude(cityName, stateCode);
            ArrayList<LinkedHashMap<String, Object>> list = restTemplate.getForObject(s, ArrayList.class);
            LinkedHashMap<String, Object> linkedHashMap = list.getFirst();

            Float lat = Float.parseFloat(linkedHashMap.get("lat").toString());
            String lon = linkedHashMap.get("lon").toString().split("\\.")[0];

            float latitude = Math.round(lat * 100) / 100;
            int longitude = Integer.parseInt(lon);
            s = findTempcutre(latitude, longitude);

            Map<String, Object> map = restTemplate.getForObject(s, Map.class);
            ;
            ApiRes res = new ApiRes();

            List<Map<String, Object>> weatherList = (List<Map<String, Object>>) map.get("weather");
            if (weatherList != null && !weatherList.isEmpty()) {
                Map<String, Object> weather = weatherList.get(0);
                res.setDescription(weather.get("description").toString());
            }

            Map<String, Object> mainWeatherData = (Map<String, Object>) map.get("main");
            if (mainWeatherData != null) {
                double tempKelvin = Double.parseDouble(mainWeatherData.get("temp").toString());
                double tempCelsius = tempKelvin - 273;
                res.setTemperature(Math.round(tempCelsius));
            }

            Map<String, Object> windData = (Map<String, Object>) map.get("wind");
            if (windData != null) {
                String windSpeed = windData.get("speed").toString() + " k/h";
                res.setWindSpeed(windSpeed);
            }
            return res;
        }catch (Exception e){
            e.getMessage();
        }
        return null;
    }
}
