package com.ninuxgithub.orderclient.config;

import com.ninuxgithub.orderclient.service.RabbitClientCallbackListener;
import com.ninuxgithub.orderclient.service.RabbitClientConfirmCallback;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.support.DefaultMessagePropertiesConverter;
import org.springframework.amqp.rabbit.support.MessagePropertiesConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.amqp.support.converter.SimpleMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.boot.autoconfigure.amqp.RabbitProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import java.util.HashMap;
import java.util.concurrent.Executors;

@Configuration
@EnableConfigurationProperties(RabbitProperties.class)
public class RabbitMQClientConfig {
    private static final Logger logger = LoggerFactory.getLogger(RabbitMQClientConfig.class);

    public static final String REPLY_QUEUE_NAME = "replyQueueName";

    public static final String REPLY_EXCHANGE_NAME = "replyExchangeName";

    public static final String REPLY_MESSAGE_KEY = "replyMessageKey";

    public static final String SEND_QUEUE_NAME = "sendQueueName";

    public static final String SEND_MESSAGE_KEY = "sendMessageKey";

    public static final String SEND_EXCHANGE_NAME = "sendExchangeName";

    @Autowired
    private RabbitProperties rabbitProperties;

    @Autowired
    private RabbitClientConfirmCallback rabbitConfirmCallback;

    @Bean
    public ConnectionFactory getConnectionFactory() {
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
        connectionFactory.setAddresses(rabbitProperties.getAddresses());
        connectionFactory.setPort(rabbitProperties.getPort());
        connectionFactory.setUsername(rabbitProperties.getUsername());
        connectionFactory.setPassword(rabbitProperties.getPassword());
        connectionFactory.setVirtualHost(rabbitProperties.getVirtualHost());
        connectionFactory.setPublisherConfirms(true); // 必须要设置
        connectionFactory.setExecutor(Executors.newFixedThreadPool(5));
        connectionFactory.setChannelCacheSize(100);
        logger.info("config Rabbitmq ConnectionFactory successfully....");
        return connectionFactory;
    }

    @Bean(name = "rabbitTemplate")
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public RabbitTemplate getRabbitTemplate() {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(getConnectionFactory());
        rabbitTemplate.setUseTemporaryReplyQueues(false);
        rabbitTemplate.setMessageConverter(getMessageConverter());
        rabbitTemplate.setMessagePropertiesConverter(getMessagePropertiesConverter());
        // rabbitTemplate.setReplyAddress(RabbitMQClientConfig.send);
        rabbitTemplate.setReceiveTimeout(200);
        rabbitTemplate.setReplyTimeout(200);
        rabbitTemplate.setConfirmCallback(rabbitConfirmCallback);
        rabbitTemplate.setReturnCallback(rabbitConfirmCallback);
        // rabbitTemplate.setChannelTransacted(true); //报错
        rabbitTemplate.setMandatory(true);
        return rabbitTemplate;
    }

    @Autowired
    private RabbitClientCallbackListener rabbitCallbackListener;

    @Bean(name = "replyMessageListenerContainer")
    public SimpleMessageListenerContainer createReplyListenerContainer() {
        SimpleMessageListenerContainer listenerContainer = new SimpleMessageListenerContainer();
        listenerContainer.setConnectionFactory(getConnectionFactory());
        listenerContainer.setQueueNames(RabbitMQClientConfig.REPLY_QUEUE_NAME);
        listenerContainer.setQueues(createReplyQueue(getRabbitAdmin()));
        listenerContainer.setMessageListener(rabbitCallbackListener);
        listenerContainer.setMessageConverter(getMessageConverter());
        listenerContainer.setMessagePropertiesConverter(getMessagePropertiesConverter());
        listenerContainer.setRabbitAdmin(getRabbitAdmin());
        listenerContainer.setAcknowledgeMode(AcknowledgeMode.MANUAL);
        //设置消费者数量
        listenerContainer.setConcurrentConsumers(3);
        listenerContainer.setMaxConcurrentConsumers(5);
        return listenerContainer;
    }

    @Bean(name = "serializerMessageConverter")
    public MessageConverter getMessageConverter() {
        return new SimpleMessageConverter();
    }

    @Bean(name = "messagePropertiesConverter")
    public MessagePropertiesConverter getMessagePropertiesConverter() {
        return new DefaultMessagePropertiesConverter();
    }

    @Bean(name = "rabbitAdmin")
    public RabbitAdmin getRabbitAdmin() {
        RabbitAdmin rabbitAdmin = new RabbitAdmin(getConnectionFactory());
        rabbitAdmin.setAutoStartup(true);
        return rabbitAdmin;
    }

    /**
     * ====================send queue=========================================
     */
    @Bean(name = "springSendMessageQueue")
    public Queue createSendQueue(@Qualifier("rabbitAdmin") RabbitAdmin rabbitAdmin) {
        Queue sendQueue = new Queue(RabbitMQClientConfig.SEND_QUEUE_NAME, true, false, false);
        rabbitAdmin.declareQueue(sendQueue);
        return sendQueue;
    }

    @Bean(name = "springSendMessageExchange")
    public Exchange createSendExchange(@Qualifier("rabbitAdmin") RabbitAdmin rabbitAdmin) {
        DirectExchange sendExchange = new DirectExchange(RabbitMQClientConfig.SEND_EXCHANGE_NAME, true, false);
        rabbitAdmin.declareExchange(sendExchange);
        return sendExchange;
    }

    @Bean(name = "springSendMessageBinding")
    public Binding createSendMessageBinding(@Qualifier("rabbitAdmin") RabbitAdmin rabbitAdmin) {
        Binding sendMessageBinding = new Binding(RabbitMQClientConfig.SEND_QUEUE_NAME, Binding.DestinationType.QUEUE,
                RabbitMQClientConfig.SEND_EXCHANGE_NAME, RabbitMQClientConfig.SEND_MESSAGE_KEY,
                new HashMap<String, Object>());
        rabbitAdmin.declareBinding(sendMessageBinding);
        return sendMessageBinding;
    }

    /**
     * ====================reply queue===========================================
     */

    @Bean(name = "springReplyMessageQueue")
    public Queue createReplyQueue(@Qualifier("rabbitAdmin") RabbitAdmin rabbitAdmin) {
        Queue replyQueue = new Queue(RabbitMQClientConfig.REPLY_QUEUE_NAME, true, false, false);
        rabbitAdmin.declareQueue(replyQueue);
        return replyQueue;
    }

    @Bean(name = "springReplyMessageExchange")
    public Exchange createReplyExchange(@Qualifier("rabbitAdmin") RabbitAdmin rabbitAdmin) {
        DirectExchange replyExchange = new DirectExchange(RabbitMQClientConfig.REPLY_EXCHANGE_NAME, true, false);
        rabbitAdmin.declareExchange(replyExchange);
        return replyExchange;
    }

    @Bean(name = "springReplyMessageBinding")
    public Binding createReplyMessageBinding(@Qualifier("rabbitAdmin") RabbitAdmin rabbitAdmin) {
        Binding replyMessageBinding = new Binding(RabbitMQClientConfig.REPLY_QUEUE_NAME, Binding.DestinationType.QUEUE,
                RabbitMQClientConfig.REPLY_EXCHANGE_NAME, RabbitMQClientConfig.REPLY_MESSAGE_KEY,
                new HashMap<String, Object>());
        rabbitAdmin.declareBinding(replyMessageBinding);
        return replyMessageBinding;
    }

}
