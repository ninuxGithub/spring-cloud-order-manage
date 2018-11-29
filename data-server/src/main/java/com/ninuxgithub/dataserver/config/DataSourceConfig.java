//package com.ninuxgithub.dataserver.config;
//
//
//import com.alibaba.druid.pool.DruidDataSource;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.env.Environment;
//
//import javax.sql.DataSource;
//
//@Configuration
//public class DataSourceConfig {
//
//    @Autowired
//    private Environment env;
//
//
//    @Bean
//    public DataSource dataSource() {
//        DruidDataSource dataSource = new DruidDataSource();
//        dataSource.setUrl(env.getProperty("spring.datasource.url"));
//        dataSource.setUsername(env.getProperty("spring.datasource.username"));//用户名
//        dataSource.setPassword(env.getProperty("spring.datasource.password"));//密码
//        dataSource.setInitialSize(2);
//        dataSource.setMaxActive(20);
//        dataSource.setMinIdle(0);
//        dataSource.setMaxWait(60000);
//        dataSource.setValidationQuery("SELECT 1");
//        dataSource.setTestOnBorrow(false);
//        dataSource.setTestWhileIdle(true);
//        dataSource.setPoolPreparedStatements(false);
//        return dataSource;
//    }
//}
