## spring-cloud-order-manage
计划采用spring cloud 搭建一个分布式的订单系统； 

* 系统的权限：
    >采用jwt(json web token) , 不同的角色的用登录后的权限不同，访问的资源也不同

* 订单的接收端
    >加入mq机制让请求加入到请求的队列（采用rabbitmq完成）    


* 订单的生成端
    >监听队列 获取队列的请求参数