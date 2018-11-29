package com.ninuxgithub.dataclientfeign.service;

import com.alibaba.fastjson.JSON;
import com.ninuxgithub.dataclientfeign.model.MessagePropety;
import com.ninuxgithub.dataclientfeign.utils.RedisUtil;
import com.rabbitmq.client.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.ChannelAwareMessageListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 监听服务端返回的结果
 */
@Component
public class RabbitClientCallbackListener implements ChannelAwareMessageListener {

    private static final Logger logger = LoggerFactory.getLogger(RabbitClientCallbackListener.class);

    public static volatile AtomicInteger index = new AtomicInteger(0);

    ThreadLocal<SimpleDateFormat> localTimeFormat = new ThreadLocal<SimpleDateFormat>() {
        @Override
        protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        }

        ;
    };


    @Autowired
    private RedisUtil redisUtil;


    @Override
    public void onMessage(Message message, Channel channel) throws Exception {
        MessageProperties messageProperties = message.getMessageProperties();

        // rabbitmq 接受消息的监听器------>真正接收消息的地方
        byte[] body = message.getBody();
        String json = null;
        try {
            json = new String(body, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        try {
            MessagePropety messagePropety = JSON.parseObject(json, MessagePropety.class);
            redisUtil.persistData(messagePropety.getUuid(), messagePropety);
            /*User user = JSON.parseObject(json, MessageProperties.class);
			persistData(user.getUuid(), user);*/
            logger.info("[RabbitMQ Client Receive from Server response] :" + json + localTimeFormat.get().format(new Date()));
        } catch (Exception e) {
            e.printStackTrace();
            logger.warn("[RabbitMQ Client Receive from Server response]  reply failed " + e.getMessage());
            try {
                channel.basicNack(messageProperties.getDeliveryTag(), false, false);
            } catch (IOException e2) {
                e2.printStackTrace();
            }
        } finally {
            try {
                channel.basicAck(messageProperties.getDeliveryTag(), false); // 确认消息成功消费
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
