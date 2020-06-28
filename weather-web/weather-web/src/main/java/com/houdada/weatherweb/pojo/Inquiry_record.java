package com.houdada.weatherweb.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class Inquiry_record implements Serializable {

    private Integer record_id;   //记录数自增
    private Integer userId;      //用户Id
    @JsonFormat(pattern ="yy-MM-dd hh:mm:ss",timezone = "GMT+8")
    private Date inquiry_time;   // 查询时间
    private String inquiry_city_value;  //查询城市名称。湖南-常德-武陵区

}
