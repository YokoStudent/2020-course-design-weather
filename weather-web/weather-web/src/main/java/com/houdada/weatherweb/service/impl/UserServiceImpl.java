package com.houdada.weatherweb.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.houdada.weatherweb.dao.UserMapper;
import com.houdada.weatherweb.pojo.User;
import com.houdada.weatherweb.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper,User> implements UserService {
}
