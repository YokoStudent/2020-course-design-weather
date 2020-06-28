package com.houdada.weatherweb.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.houdada.weatherweb.dao.Inquiry_recordMapper;
import com.houdada.weatherweb.pojo.Inquiry_record;
import com.houdada.weatherweb.service.Inquiry_recordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;


@Service
public class Inquiry_recordServiceImpl extends ServiceImpl<Inquiry_recordMapper,Inquiry_record> implements Inquiry_recordService {

    @Autowired
    private Inquiry_recordMapper inquiry_recordMapper;

    @Override
    public List<String> getRecentCitys(Integer userID ,Date afterWeek, Date now) {
        return inquiry_recordMapper.getRecentCitys(userID,afterWeek,now);
    }

    @Override
    public int getCountByUserIDAndTime(int userId, Date odian, Date now) {
        return inquiry_recordMapper.getCountByUserIDAndTime(userId,odian,now);
    }
}
