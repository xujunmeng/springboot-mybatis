package com.demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author james
 * @date 2018/6/11
 */
@SpringBootApplication
@MapperScan(value = "com.demo.dao")
@EnableTransactionManagement(proxyTargetClass = true)
@EnableAsync
public class DemoApplication{

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class,args);
    }
}
