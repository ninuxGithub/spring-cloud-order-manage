package com.ninuxgithub.orderserver.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ninuxgithub.orderserver.config.RabbitMQServerConfig;
import com.ninuxgithub.orderserver.model.MessagePropety;
import com.rabbitmq.client.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.ChannelAwareMessageListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 监听客户端发送的请求
 */
@Component
public class RabbitServerCallbackListener implements ChannelAwareMessageListener {
    private static final Logger logger = LoggerFactory.getLogger(RabbitServerCallbackListener.class);

    public static volatile AtomicInteger index = new AtomicInteger(0);

    ThreadLocal<SimpleDateFormat> localTimeFormat = new ThreadLocal<SimpleDateFormat>() {
        @Override
        protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        }

        ;
    };

    private static final String url ="http://10.1.51.96:8005/api/findProductById?id=";

    private static final RestTemplate restTemplate = new RestTemplate();

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @SuppressWarnings("static-access")
	@Override
    public void onMessage(Message message, Channel channel) throws Exception {
        MessageProperties messageProperties = message.getMessageProperties();

        // rabbitmq 接受消息的监听器------>真正接收消息的地方
        byte[] body = message.getBody();
        String json = null;
        try {
            json = new String(body, "UTF-8");
        } catch (UnsupportedEncodingException e4) {
            e4.printStackTrace();
        }

        try {
            logger.info("[RabbitMQ Server Reply] :" + json);
            JSONObject paramsData = JSONObject.parseObject(json);
            String data = paramsData.getString("data");
            String uuid = paramsData.getString("uuid");
            String requestType = paramsData.getString("requestType");

            if(requestType.equals("buyGood")){

            }
            MessagePropety messagePropety= new MessagePropety();
            String result   = restTemplate.getForEntity(url + data, String.class).getBody();
            messagePropety.setUuid(uuid);
            messagePropety.setData(result);
            messagePropety.setRequestType(requestType);
           
            Thread.currentThread().sleep(2000);


            CorrelationData correlationData = new CorrelationData(UUID.randomUUID().toString());
            String jsonMessage = JSON.toJSONString(messagePropety);
            Message msg = new Message(jsonMessage.getBytes(), new MessageProperties());
            
            rabbitTemplate.convertAndSend(RabbitMQServerConfig.REPLY_EXCHANGE_NAME, RabbitMQServerConfig.REPLY_MESSAGE_KEY, msg, correlationData);


        } catch (Exception e) {
            logger.warn("[RabbitMQ Server Reply]  reply failed " + e.getMessage());
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
