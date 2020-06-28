package com.houdada.weatherweb.service;

import com.houdada.weatherweb.pojo.WeatherResponse;

public interface WeatherService {

    WeatherResponse getByCityId(String id);
}
