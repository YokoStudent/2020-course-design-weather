package com.houdada.weatherweb.pojo;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class WeatherResponse implements Serializable {
    private String city ;
    private Today today ;
    private List<Forecast> forecastList ;
}
