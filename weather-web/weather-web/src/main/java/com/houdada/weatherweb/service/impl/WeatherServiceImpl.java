package com.houdada.weatherweb.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.houdada.weatherweb.pojo.Forecast;
import com.houdada.weatherweb.pojo.Today;
import com.houdada.weatherweb.pojo.WeatherResponse;
import com.houdada.weatherweb.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class WeatherServiceImpl implements WeatherService {

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public WeatherResponse getByCityId(String id)  {
        // 请求url
        String url="http://t.weather.sojson.com/api/weather/city/"+id;

        // 返回数据
        WeatherResponse weather = null;

        ResponseEntity<JSONObject> entity = restTemplate.getForEntity(url, JSONObject.class);

        // 响应成功处理数据
        if(entity.getStatusCodeValue()== 200) {

            weather = new WeatherResponse();
            weather.setForecastList(new ArrayList<>());

            JSONObject body = entity.getBody() ;

            JSONObject data = body.getJSONObject("data");

            JSONObject cityInfo = body.getJSONObject("cityInfo");

            JSONArray forecast = data.getJSONArray("forecast");

            weather.setCity(cityInfo.getString("city"));

            for (int i = 0; i < forecast.size(); i++) {
                JSONObject jsonObject = forecast.getJSONObject(i) ;
                if(i == 0){
                    //设置今日天气
                    Today today = new Today() ;
                    today.setDate(jsonObject.getDate("ymd"));
                    today.setAqi(jsonObject.getInteger("aqi"));
                    today.setFl(jsonObject.getString("fl"));
                    today.setFx(jsonObject.getString("fx"));
                    today.setHigh(jsonObject.getString("hign"));
                    today.setLow(jsonObject.getString("low"));
                    today.setSunrise(jsonObject.getString("sunrise"));
                    today.setSunset(jsonObject.getString("sunset"));
                    today.setNotice(jsonObject.getString("notice"));
                    today.setType(jsonObject.getString("type"));
                    today.setWeek(jsonObject.getString("week"));
                    weather.setToday(today);
                }
                //处理未来n天天气
                Forecast forecast1 = new Forecast();
                forecast1.setDate(jsonObject.getDate("ymd"));
                forecast1.setHigh(StringToNum(jsonObject.getString("high")));
                forecast1.setLow(StringToNum(jsonObject.getString("low")));
                forecast1.setNotice(jsonObject.getString("notice"));
                weather.getForecastList().add(forecast1) ;
            }
        }

        return weather;
    }


    //提取数字
    //高温 33℃ ===> 33
    public Integer StringToNum(String resouce){
        String regEx="[^0-9]";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(resouce);
        return Integer.valueOf(m.replaceAll("").trim());
    }
}
