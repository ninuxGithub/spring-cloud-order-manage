package com.ninuxgithub.orderclient.controller;


import com.alibaba.fastjson.JSON;
import com.ninuxgithub.orderclient.model.MessagePropety;
import com.ninuxgithub.orderclient.service.SendMessageService;
import com.ninuxgithub.orderclient.utils.RedisUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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


    @RequestMapping(value = "/mq/buyGood", method = RequestMethod.GET)
    public void buyGood(MessagePropety messagePropety) {
        logger.info("request message property : " + messagePropety.toString());
        sendMessageService.sendMessage(messagePropety);
    }


    @RequestMapping(value = "/mq/flushTask", method = RequestMethod.GET)
    public Map<String, Object> flushTask(HttpServletRequest request) throws InterruptedException {
        Map<String, Object> map = new HashMap<>();
        String result = redisUtil.get(request.getParameter("uuid"));
        MessagePropety messagePropety = null;
        if (null != request && !"".equals(result)) {
            System.out.println("result is  :  "+result);
            messagePropety = JSON.parseObject(result, MessagePropety.class);

           /* String data = (String) messagePropety.getData();
            System.out.println(data);*/
            /*Object data = messagePropety.getData();
            Product product = (Product) data;*/


        }
        map.put("result", messagePropety);
        return map;
    }
}
