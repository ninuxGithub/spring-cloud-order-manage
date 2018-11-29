package com.ninuxgithub.dataserver.service.impl;

import com.codingapi.tx.netty.service.TxManagerHttpRequestService;
import com.lorne.core.framework.utils.http.HttpUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class TxManagerHttpRequestServiceImpl implements TxManagerHttpRequestService {
    private static final Logger logger = LoggerFactory.getLogger(TxManagerHttpRequestServiceImpl.class);

    @Override
    public String httpGet(String url) {
        logger.info("httpGet-start");
        String res = HttpUtils.get(url);
        logger.info("httpGet-end");
        return res;
    }

    @Override
    public String httpPost(String url, String params) {
        logger.info("httpPost-start");
        String res = HttpUtils.post(url,params);
        logger.info("httpPost-end");
        return res;
    }
}
