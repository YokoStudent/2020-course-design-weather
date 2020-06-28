package com.houdada.weatherweb.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.houdada.weatherweb.pojo.Inquiry_record;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
@Mapper
public interface Inquiry_recordMapper extends BaseMapper<Inquiry_record> {

    @Select("select count(*) from inquiry_record where user_id = #{userId} and inquiry_time between #{odian} and #{now}")
    int getCountByUserIDAndTime(@Param("userId")int userId,@Param("odian") Date odian,@Param("now") Date now);

    @Select("SELECT inquiry_city_value FROM (SELECT inquiry_city_value,COUNT(*) AS SUM FROM inquiry_record WHERE user_id = #{userID} and inquiry_time BETWEEN #{afterWeek} AND #{now} GROUP BY inquiry_city_value ORDER BY SUM DESC) a LIMIT 5;")
    List<String> getRecentCitys(@Param("userID") Integer userID,@Param("afterWeek") Date afterWeek,@Param("now") Date now);
}
