package com.demo;

import com.aihuishou.common.alarm.annotation.EnableAlarm;
import com.aihuishou.common.db.annotation.EnableDBSlave;
import com.aihuishou.common.db.datasource.MasterSlaveDataSourceInterceptor;
import com.demo.config.DynamicDataSourceRegister;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author james
 * @date 2018/6/11
 */
@Import({DynamicDataSourceRegister.class})
@EnableDBSlave
@SpringBootApplication
@MapperScan(value = "com.demo.dao")
@EnableAlarm
@EnableTransactionManagement(proxyTargetClass = true)
@EnableAspectJAutoProxy
@EnableAsync(proxyTargetClass = true)
public class DemoApplication{

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class,args);
    }

}
