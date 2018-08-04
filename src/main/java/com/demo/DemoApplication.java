package com.demo;

import com.aihuishou.common.alarm.annotation.EnableAlarm;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author james
 * @date 2018/6/11
 */
@SpringBootApplication
@MapperScan(value = "com.demo.dao")
@EnableAlarm
public class DemoApplication{

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class,args);
    }
}
