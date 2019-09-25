package com.demo.dao;

import com.demo.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by james on 2018/8/27.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class UserMapperTest {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    public StringRedisTemplate redisTemplate;

    @Test
    public void testSelectByPrimaryKey() throws Exception {
        User user = userMapper.selectByPrimaryKey(1);
        System.out.println(user);
    }
}