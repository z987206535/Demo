package com.btyer.test;

import org.junit.Test;
import org.springframework.data.redis.core.RedisTemplate;
import javax.annotation.Resource;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Set;

/**
 * 描述：redis缓存集成测试
 *
 * @author btyer
 * @create 2019/4/9 21:29
 */
public class RedisTest extends BaseJunit4Test {

    @Resource
    private RedisTemplate redisTemplate;

    @Test
   public void  testRedis(){
       redisTemplate.opsForValue().set("name","btyer");
       //String name = (String)redisTemplate.opsForValue().get("name");
      Set<String> list = redisTemplate.opsForSet().members("3");
      Set<String> lists = redisTemplate.opsForSet().members("springmvc_mybatis.com.btyer.mood.id.list.key");

      System.out.println("Value of name is "+list);
      System.out.println("Value of name is "+lists);

    }
}
