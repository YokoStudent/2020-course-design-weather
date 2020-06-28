package com.houdada.weatherweb.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.houdada.weatherweb.pojo.Inquiry_record;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface Inquiry_recordService extends IService<Inquiry_record>  {

    int getCountByUserIDAndTime(int userId, Date odian, Date now);

    List<String> getRecentCitys(Integer userID ,Date afterWeek,Date now);
}
