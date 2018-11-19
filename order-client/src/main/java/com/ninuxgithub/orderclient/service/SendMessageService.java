package com.ninuxgithub.orderclient.service;

import com.alibaba.fastjson.JSONObject;
import com.ninuxgithub.orderclient.config.RabbitMQClientConfig;
import com.ninuxgithub.orderclient.model.MessagePropety;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SendMessageService {

    private static final Logger logger = LoggerFactory.getLogger(SendMessageService.class);

    private RabbitTemplate rabbitTemplate;

    /**
     * 构造方法注入
     */
    @Autowired
    public SendMessageService(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }


    /**
     * 发送消息
     *
     * @param messagePropety
     */
    public void sendMessage(MessagePropety messagePropety) {
        String uuid = messagePropety.getUuid();
        CorrelationData correlationData = new CorrelationData(uuid);
        String json = JSONObject.toJSONString(messagePropety);
        rabbitTemplate.convertAndSend(RabbitMQClientConfig.SEND_EXCHANGE_NAME, RabbitMQClientConfig.SEND_MESSAGE_KEY, json, correlationData);

    }

}
