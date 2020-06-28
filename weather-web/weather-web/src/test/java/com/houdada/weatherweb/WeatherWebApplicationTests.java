package com.houdada.weatherweb;

import com.houdada.weatherweb.pojo.WeatherResponse;
import com.houdada.weatherweb.service.WeatherService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import javax.xml.crypto.Data;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

@SpringBootTest
class WeatherWebApplicationTests {

	@Autowired
	private StringRedisTemplate stringRedisTemplate ;

	@Autowired
	private WeatherService weatherService ;

	@Test
	void test02(){
		ValueOperations<String, String> ops = stringRedisTemplate.opsForValue();
		String s = ops.get("127.0.0.1getTimes");
		System.out.println(s);
	}

	@Test
	void test01(){
		WeatherResponse weatherResponse = weatherService.getByCityId("101251401");
		System.out.println(weatherResponse);
	}

	@Test
	void redisTest(){
		ValueOperations<String, String> stringStringValueOperations = stringRedisTemplate.opsForValue();
		//stringStringValueOperations.set("test","yoko");
		String test = stringStringValueOperations.get("474539dd2f564c4988faa555a2ba91cb");
		System.out.println(test);
	}


	@Test
	void contextLoads() {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DAY_OF_YEAR, 1);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.MILLISECOND, 0);
		System.out.println((cal.getTimeInMillis() - System.currentTimeMillis()) / 1000);
	}
}
