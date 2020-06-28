package com.houdada.weatherweb.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.houdada.weatherweb.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface UserMapper extends BaseMapper<User> {
}
