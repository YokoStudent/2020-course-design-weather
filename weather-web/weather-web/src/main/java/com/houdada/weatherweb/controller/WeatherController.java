package com.houdada.weatherweb.controller;

import com.houdada.weatherweb.pojo.Inquiry_record;
import com.houdada.weatherweb.pojo.WeatherResponse;
import com.houdada.weatherweb.service.Inquiry_recordService;
import com.houdada.weatherweb.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/weather")
public class WeatherController {

    @Autowired
    private WeatherService weatherService;

    @Autowired
    private Inquiry_recordService inquiryRecordService;

    @Autowired
    private StringRedisTemplate redisTemplate;

    /**
     * @author houcheng
     * @param cityID
     * @param request
     * @return
     */
    @GetMapping("/getWeather")
    public Object weatherById(@RequestParam("cityID") String cityID,
                              HttpServletRequest request) {
        Map<String,Object> map = new HashMap<>() ;

        //请求头token
        String accessToken = request.getHeader("accessToken");

        if(null == accessToken){
            accessToken = "" ;
        }

        ValueOperations<String, String> opsForValue = redisTemplate.opsForValue();
        String s = opsForValue.get(accessToken);

        //如果是登录状态
        if(s != null){
            WeatherResponse weatherResponse = weatherService.getByCityId(cityID);

            int userId=Integer.parseInt(s);

            //将本次查询存入数据库
            Inquiry_record inquiry_record = new Inquiry_record();
            inquiry_record.setUserId(userId);
            inquiry_record.setInquiry_time(new Date());
            inquiry_record.setInquiry_city_value(weatherResponse.getCity());
            inquiryRecordService.save(inquiry_record);

            //返回状态
            map.put("status",200) ;
            map.put("msg","成功") ;
            map.put("data",weatherResponse) ;
        }else {
            //游客状态
            //ip查询今日次数是否已到达
            if(opsForValue.get(request.getRemoteAddr()+"getWeather")!=null){
                //从redis中查询客户端IP地址 redis中存在则无权限访问，只能查询一次
                map.put("status",403);
                map.put("msg","今日查询次数已达上限");
            }else {
                //redis中无此IP,可以查询天气，并将IP存入redis缓存中
                WeatherResponse weatherResponse = weatherService.getByCityId(cityID);

                Calendar cal = Calendar.getInstance();
                cal.add(Calendar.DAY_OF_YEAR, 1);
                cal.set(Calendar.HOUR_OF_DAY, 0);
                cal.set(Calendar.SECOND, 0);
                cal.set(Calendar.MINUTE, 0);
                cal.set(Calendar.MILLISECOND, 0);
                long l = (cal.getTimeInMillis() - System.currentTimeMillis()) / 1000;
                opsForValue.set(request.getRemoteAddr()+"getWeather","今日已查询",l, TimeUnit.SECONDS);  //设置ip缓存每天凌晨过期

                map.put("status",200);
                map.put("msg","成功");
                map.put("data",weatherResponse);
            }
        }

        return map ;
    }

    /**
     * @author houcheng
     * @param request
     * @return
     */
    @GetMapping("/getTimes")
    public Object getTimes(HttpServletRequest request){
        Map<String,Object> map = new HashMap<>() ;
        //请求头token
        String accessToken = request.getHeader("accessToken");

        if(null == accessToken){
            accessToken = "" ;
        }

        ValueOperations<String, String> ops = redisTemplate.opsForValue();

        String s = ops.get(accessToken);

        //1. 如果是登录状态 查数据库
        if(s != null){
            long current=System.currentTimeMillis();
            long zero=current/(1000*3600*24)*(1000*3600*24)- TimeZone.getDefault().getRawOffset();
            Date otime=new Date(zero);
            Date now=new Date();
            int count = inquiryRecordService.getCountByUserIDAndTime(Integer.parseInt(s), otime, now);
            map.put("times",count);
        }else {
            // 2. 如果是游客登录，查redis
            // 如果有ip记录
            // 本系统要求是每日仅一次，如果是多次，可用list，获取长度
            if(ops.get(request.getRemoteAddr()+"getWeather")!=null){
                map.put("times",1);
            }else {
                map.put("times",0);
            }
        }
        map.put("status",200);
        map.put("msg","成功");
        return map ;
    }

    /**
     * @author houcheng
     * @param request
     * @return
     */
    @GetMapping("/getRecentCitys")
    public Object getRecentCitys(HttpServletRequest request){
        Map<String,Object> map=new HashMap<>();

        //请求头token
        String accessToken = request.getHeader("accessToken");

        if(null == accessToken){
            accessToken = "" ;
        }

        ValueOperations<String, String> ops = redisTemplate.opsForValue();
        String s = ops.get(accessToken);

        if(s != null){
            long nowtime = System.currentTimeMillis();
            long afterweek=nowtime-604800*1000;
            Date now = new Date(nowtime);
            Date afterWeek=new Date(afterweek);
            List<String> citys = inquiryRecordService.getRecentCitys(Integer.parseInt(s),afterWeek, now);
            map.put("status",200);
            map.put("msg","成功");
            map.put("data",citys);
        }else {
            map.put("status",403);
            map.put("msg","登录状态错误");
        }

        return map;
    }

}
