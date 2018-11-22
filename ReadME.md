## spring-cloud-order-manage
计划采用spring cloud 搭建一个分布式的订单系统； 

* 系统的权限：
    >采用jwt(json web token) , 不同的角色的用登录后的权限不同，访问的资源也不同

* 订单的接收端
    >加入mq机制让请求加入到请求的队列（采用rabbitmq完成）    


* 订单的生成端
    >监听队列 获取队列的请求参数
    
    
    
    
### ide 修改自动生效
```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-devtools</artifactId>
    <optional>true</optional>
</dependency>

<plugin>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-maven-plugin</artifactId>
    <configuration>
        <fork>true</fork>
    </configuration>
</plugin>

```

setting:  compiler  : build project automatically true
ctrl+alt+shift +/  -->registry --> compiler.automake.allow.when.app.running true

