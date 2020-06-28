package com.houdada.weatherweb.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * ClassName Today.java
 * Description TODO
 * User: Yoko
 * Date: 2020/6/28 15:42
 */
@Data
public class Today implements Serializable {
    //"date": "2020-06-25",
    //"high": "高温 29℃",
    //"low": "低温 26℃",
    //"week": "星期四",
    //"sunrise": "05:41",
    //"sunset": "19:29",
    //"aqi": 16,
    //"fx": "南风",
    //"fl": "2级",
    //"type": "大雨",
    //"notice": "出门最好穿雨衣，勿挡视线"

    @JsonFormat(pattern = "yyyy-MM-dd",timezone ="GMT+8")
    private Date date;
    private String high;
    private String low;
    private String week;
    private String sunrise;
    private String sunset;
    private Integer aqi;
    private String fx;
    private String fl;
    private String type;
    private String notice;
}
