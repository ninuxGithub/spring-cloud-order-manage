package com.ninuxgithub.dataclientfeign.controller;


import com.alibaba.fastjson.JSON;
import com.ninuxgithub.dataclientfeign.model.MessagePropety;
import com.ninuxgithub.dataclientfeign.model.Order;
import com.ninuxgithub.dataclientfeign.model.Product;
import com.ninuxgithub.dataclientfeign.service.SendMessageService;
import com.ninuxgithub.dataclientfeign.utils.RedisUtil;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@RestController
public class MqRequestController {

    private static final Logger logger = LoggerFactory.getLogger(MqRequestController.class);


    @Autowired
    RedisUtil redisUtil;

    @Autowired
    SendMessageService sendMessageService;

    RestTemplate restTemplate = new RestTemplate();


    @RequestMapping(value = "/mq/buyGood", method = RequestMethod.GET)
    public void buyGood(MessagePropety messagePropety) {
        logger.info("request message property : " + messagePropety.toString());
        sendMessageService.sendMessage(messagePropety);
    }


    @RequestMapping(value = "/mq/flushTask", method = RequestMethod.GET)
    public Map<String, Object> flushTask(HttpServletRequest request) throws InterruptedException {
        Map<String, Object> map = new HashMap<>();
        String uid = request.getParameter("uid");
        String uuid = request.getParameter("uuid");
        String result = redisUtil.get(uuid);

        MessagePropety messagePropety = null;
        if (StringUtils.isNotBlank(result) && !result.equals("null")) {
            messagePropety = JSON.parseObject(result, MessagePropety.class);
            Product product  = JSON.parseObject(messagePropety.getData().toString(), Product.class);
            logger.info(product.toString());
            String pid = product.getId();
            ResponseEntity<Order> entity = restTemplate.getForEntity("http://10.1.51.96:8005/api/saveOrder?pid=" + pid + "&uid=" + uid, Order.class);
            Order order = entity.getBody();
            if(order != null){

            }
            System.out.println(order);
        }
        map.put("result", messagePropety);
        return map;
    }
}
