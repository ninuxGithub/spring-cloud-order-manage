package com.ninuxgithub.dataserver;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;

import javax.sql.DataSource;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
//spring boot 2.x  兼容lcn的解决方案：https://www.cnblogs.com/sxdcgaq8080/p/9776695.html
//参考：https://jadyer.cn/2017/01/19/springcloud-ribbon-feign/
public class DataServerApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(DataServerApplication.class, args);
    }



    @Autowired
    private Environment env;

    @Bean
    public DataSource dataSource() {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setUrl(env.getProperty("spring.datasource.url"));
        dataSource.setUsername(env.getProperty("spring.datasource.username"));//用户名
        dataSource.setPassword(env.getProperty("spring.datasource.password"));//密码
        dataSource.setInitialSize(2);
        dataSource.setMaxActive(20);
        dataSource.setMinIdle(0);
        dataSource.setMaxWait(60000);
        dataSource.setValidationQuery("SELECT 1");
        dataSource.setTestOnBorrow(false);
        dataSource.setTestWhileIdle(true);
        dataSource.setPoolPreparedStatements(false);
        return dataSource;
    }


//    @Autowired
//    ProductRepository productRepository;

    /**
     * 初始化产品列表
     *
     * @param args
     * @throws Exception
     */
    @Override
    public void run(String... args) throws Exception {
       /* List<Product> reposDataList = productRepository.findAll();
        if (CollectionUtils.isEmpty(reposDataList)) {
            List<Product> products = Arrays.asList(
                    new Product("小米MIX", 3499d, "5月9日-21日享花呗12期分期免息", "pinpai1.png", Type.StarType, 78),
                    new Product("小米5s", 1999d, "5月9日-10日，下单立减200元", "pinpai2.png", Type.StarType, 252),
                    new Product("小米手机5 64GB", 1799d, "5月9日-10日，下单立减100元", "pinpai3.png", Type.StarType, 855),
                    new Product("小米电视3s 55英寸", 3999d, "5月9日，下单立减200元", "pinpai4.png", Type.StarType, 916),
                    new Product("小米笔记本", 3599d, "更轻更薄，像杂志一样随身携带", "pinpai5.png", Type.StarType, 312),

                    new Product("小米6", 49d, "小米6 硅胶保护套", "peijian2.jpg", Type.fit, 1098),
                    new Product("小米6", 49d, "小米6 硅胶保护套", "peijian2.jpg", Type.fit, 2655),
                    new Product("小米手机4c", 29d, "小米手机4c 小米4c 智能", "peijian3.jpg", Type.fit, 2532),
                    new Product("红米NOTE 4X", 49d, "红米NOTE 4X 红米note4X", "peijian4.jpg", Type.fit, 2232),
                    new Product("小米自拍杆", 89d, "小米支架式自拍杆", "peijian5.jpg", Type.fit, 2290),
                    new Product("蓝牙音箱", 129d, "小钢炮蓝牙音箱2", "peijian7.jpg", Type.fit, 2590),
                    new Product("指环支架", 19d, "小米指环支架", "peijian7.jpg", Type.fit, 2865),
                    new Product("米家随身风扇", 19.9d, "米家随身风扇", "peijian8.jpg", Type.fit, 2765),
                    new Product("红米4X 高透软胶保护套", 59d, "红米4X 高透软胶保护套", "peijian9.jpg", Type.fit, 1786),
                    new Product("红米4X 高透软胶保护套", 59d, "红米4X 高透软胶保护套", "peijian9.jpg", Type.fit, 900)
            );

            for (Product product : products) {
                productRepository.save(product);
            }
        }*/


    }
}
