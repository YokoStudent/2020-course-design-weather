package com.houdada.weatherweb.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.houdada.weatherweb.pojo.User;
import com.houdada.weatherweb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/user")
public class LoginController {

    @Autowired
    private UserService userService;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    /**
     * @author houcheng
     * @param userlogin
     * @param session
     * @return
     */
    @PostMapping("/login")
    public Object login(@RequestBody User userlogin, HttpSession session){
        Map<String,Object> map=new HashMap<>();
        QueryWrapper<User> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("username",userlogin.getUsername());
        User user = userService.getOne(queryWrapper,false);

        //1.用户是否存在
        if(user ==null){
            map.put("status",403);
            map.put("msg","不存在用户");
            return map;
        }

        //2.密码是否正确
        String password = DigestUtils.md5DigestAsHex(userlogin.getPassword().getBytes()) ;
        if(user.getPassword().equals(password)){
            UUID uuid = UUID.randomUUID();
            String token = uuid.toString().trim().replaceAll("-","");

            ValueOperations<String,String> StringOperations = stringRedisTemplate.opsForValue();
            StringOperations.set(token,user.getId().toString(),60*10,TimeUnit.SECONDS);

            System.out.println(token);
            map.put("status",200);
            map.put("msg","登录成功");
            map.put("token", token);
            map.put("username",userlogin.getUsername());
        }else {
            map.put("status",403);
            map.put("msg","密码错误");
        }
        return map;
    }


    /**
     * @author houcheng
     * @param user
     * @return
     */
    @PostMapping("/register")
    public Object register(@RequestBody User user){
        Map<String,Object> map=new HashMap<>();

        //1.判断用户是否已存在
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username",user.getUsername()) ;
        User one = userService.getOne(queryWrapper,false);
        if(null != one){
            map.put("status",403);
            map.put("msg","用户名已存在");
            return map;
        }

        //2.创建时间
        user.setCreateTime(new Date());

        //3.密码md5加密
        String password = DigestUtils.md5DigestAsHex(user.getPassword().getBytes());
        user.setPassword(password);

        boolean save = userService.save(user);
        if (save){
            map.put("status",200);
            map.put("msg","注册成功");
        }
        return map;
    }

    /**
     * @author houcheng
     * @param request
     * @return
     */
    @GetMapping("/logout")
    public Object logout(HttpServletRequest request){
        String token = request.getHeader("accessToken");
        stringRedisTemplate.delete(token);
        return "success";
    }
}
