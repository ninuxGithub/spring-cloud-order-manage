package com.ninuxgithub.dataserver.service.impl;

import com.codingapi.tx.annotation.TxTransaction;
import com.ninuxgithub.dataserver.model.Pong;
import com.ninuxgithub.dataserver.repository.PongRepository;
import com.ninuxgithub.dataserver.service.FeignService;
import com.ninuxgithub.dataserver.service.PongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
public class PongServiceImpl implements PongService {

    @Autowired
    private PongRepository pongRepository;


    @Autowired
    FeignService feignService;



    @Override
    @TxTransaction(isStart = true)
    @Transactional(rollbackFor = Exception.class)
    public Pong savePingPong(boolean flag) {
        feignService.savePing();
        Pong pong = pongRepository.save(new Pong(new Date()));
        if(flag){
            int v = 100/0;
        }
        return pong;
    }
}
