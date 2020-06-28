package com.houdada.weatherweb.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * ClassName Forecast.java
 * Description TODO
 * User: Yoko
 * Date: 2020/6/28 15:42
 */
@Data
public class Forecast implements Serializable {
    //"date": "2020-06-25",
    //"high": "31",
    //"low": "27",
    //"notice": "雨虽小，注意保暖别感冒"

    @JsonFormat(pattern = "yyyy-MM-dd",timezone ="GMT+8")
    private Date date ;
    private Integer high ;
    private Integer low ;
    private String notice ;
}
