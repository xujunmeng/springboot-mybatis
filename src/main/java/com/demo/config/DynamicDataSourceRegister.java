package com.demo.config;

import com.aihuishou.common.db.datasource.MasterSlaveRoutingDataSource;
import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.commons.dbcp2.BasicDataSourceFactory;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.boot.bind.RelaxedPropertyResolver;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotationMetadata;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * @author james
 * @date 2018/8/10
 */
public class DynamicDataSourceRegister implements ImportBeanDefinitionRegistrar, EnvironmentAware {

    /**
     * 默认数据源
     */
    private DataSource masterDataSource;

    /**
     * 更多数据源
     */
    private Map<String, DataSource> slaveDataSourceMap = new HashMap<>();

    @Override
    public void setEnvironment(Environment environment) {
        System.out.println("DynamicDataSourceRegister.setEnvironment()");

        initDefaultDataSource(environment);

        initCustomDataSources(environment);
    }

    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        // 创建DynamicDataSource

        GenericBeanDefinition beanDefinition = new GenericBeanDefinition();
        beanDefinition.setBeanClass(MasterSlaveRoutingDataSource.class);
        beanDefinition.setSynthetic(true);

        MutablePropertyValues mpv = beanDefinition.getPropertyValues();

        mpv.addPropertyValue("masterDataSource", masterDataSource);
        mpv.addPropertyValue("slaveDataSourceMap", slaveDataSourceMap);

        registry.registerBeanDefinition("dataSource", beanDefinition);
    }




    /**
     * 加载主数据源配置.
     * @param env
     */
    private void initDefaultDataSource(Environment env){

        // 读取主数据源
        RelaxedPropertyResolver propertyResolver = new RelaxedPropertyResolver(env, "master.datasource");

        Map<String, Object> dsMap = propertyResolver.getSubProperties(".");

        //创建数据源;
        masterDataSource = buildDataSource(dsMap);
    }

    /**
     * 初始化更多数据源
     */
    private void initCustomDataSources(Environment env) {
        // 读取配置文件获取更多数据源，也可以通过defaultDataSource读取数据库获取更多数据源
        RelaxedPropertyResolver propertyResolver = new RelaxedPropertyResolver(env, "slaver.datasource.");

        String dsPrefixs = propertyResolver.getProperty("names");

        /**
         * 多个数据源
         */
        for (String dsPrefix : dsPrefixs.split(",")) {

            Map<String, Object> dsMap = propertyResolver.getSubProperties(dsPrefix + ".");
            DataSource ds = buildDataSource(dsMap);

            slaveDataSourceMap.put(dsPrefix, ds);
        }
    }



    /**
     * 创建datasource.
     * @return
     */
    @SuppressWarnings("unchecked")
    public DataSource buildDataSource(Map<String, Object> dsMap) {

        Properties properties = new Properties();
        for (Map.Entry<String, Object> entry : dsMap.entrySet()) {
            properties.setProperty(entry.getKey(), (String) entry.getValue());
        }

        try {
            BasicDataSource dataSource = BasicDataSourceFactory.createDataSource(properties);
            return dataSource;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
