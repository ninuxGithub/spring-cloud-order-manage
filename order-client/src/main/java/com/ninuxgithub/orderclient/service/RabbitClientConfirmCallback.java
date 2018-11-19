package com.ninuxgithub.orderclient.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate.ConfirmCallback;
import org.springframework.amqp.rabbit.core.RabbitTemplate.ReturnCallback;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 消息接收 消息确认 以及route错误后返回的消息
 */
@Component
@SuppressWarnings("deprecation")
public class RabbitClientConfirmCallback implements ConfirmCallback, ReturnCallback {
    private static final Logger logger = LoggerFactory.getLogger(RabbitClientConfirmCallback.class);

    public static volatile AtomicInteger index = new AtomicInteger(0);

//	@Autowired
//	private RabbitConfirmMessageRepository rabbitConfirmMessageRepository;

    ThreadLocal<SimpleDateFormat> localTimeFormat = new ThreadLocal<SimpleDateFormat>() {
        @Override
        protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        }

        ;
    };

    /**
     * 返回消息:如果rabbitTemplate.convertAndSend(RabbitConfig.EXCHANGE,
     * RabbitConfig.ROUTINGKEY, content, correlationId);
     * 的routeKey错误，那么returnedMessage会执行
     */
    @Override
    public void returnedMessage(Message message, int replyCode, String replyText, String exchange, String routingKey) {
        String msgId = "";
        if (message.getMessageProperties().getCorrelationId() != null) {
            msgId = new String(message.getMessageProperties().getCorrelationId());
        }
        logger.info("[RabbitReturnCallback]: msgId:" + msgId + ",msgBody:" + new String(message.getBody()) + ",replyCode:"
                + replyCode + ",replyText:" + replyText + ",exchange:" + exchange + ",routingKey:" + routingKey);
    }

    /**
     * 消息确认：都会执行的 * 只是ack:true , 成功接收 当ack:false, 没有接收到消息
     */
    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        //rabbitConfirmMessageRepository.save(new RabbitConfirmMessage(correlationData.getId(), ack, cause));
        logger.info("[RabbitConfirmCallback]:" + correlationData + ",ack:" + (ack ? "接收消息成功" : "接收消息失败"));
    }

}
