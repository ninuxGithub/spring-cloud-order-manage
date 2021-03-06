## spring-cloud-order-manage
计划采用spring cloud 搭建一个分布式的订单系统； 

* 系统的权限：
    >采用jwt(json web token) , 不同的角色的用登录后的权限不同，访问的资源也不同

* 订单的接收端
    >加入mq机制让请求加入到请求的队列（采用rabbitmq完成）    


* 订单的生成端
    >监听队列 获取队列的请求参数
    
* tx-lcn分布式事物管理
    >对分布式节点的事物管理
    
    
    
    
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
项目tx-lcn deploy到本地为了支持springcloud 2.x  
来自：https://www.cnblogs.com/sxdcgaq8080/p/9776695.html解决方案

执行命令install到本地：

    mvn clean install  -DskipTests  -Dmaven.javadoc.skip=true 
    mvn install:install-file -DgroupId=com.codingapi -DartifactId=transaction-springcloud -Dversion=4.2.0 -Dpackaging=jar -Dfile=D:\dev\live2\tx-lcn\transaction-springcloud\target\transaction-springcloud-4.2.0.jar
    mvn install:install-file -DgroupId=com.codingapi -DartifactId=transaction-springcloud -Dversion=4.2.0 -Dpackaging=jar -Dfile=D:\dev\live2\tx-lcn\tx-plugins-db\target\tx-plugins-db-4.2.0.jar

## tx-manager
    spring cloud 分布式事物


### 工作日志
```log
2018-11-23 14:52:21 添加前端用户体系， 注册， 登录
2018-11-26 17:52:24 加入购买-->请求加入到队列-->请求消费队列->restTamplate请求接口获取商品详细，保存到redis ->轮询获取商品的详细
2018-11-28 17:42:38 加入订单对象关联产品用户
2018-11-30 18:20:58 加入spring cloud 分布式事物管理 tx-manager 子模块
2018-12-20 18:46:13 加入lcn + lcn分布式事物的测试demo
```

