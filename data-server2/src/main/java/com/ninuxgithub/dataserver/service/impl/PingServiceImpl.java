package com.ninuxgithub.dataserver.service.impl;

import com.codingapi.tx.annotation.TxTransaction;
import com.ninuxgithub.dataserver.model.Ping;
import com.ninuxgithub.dataserver.repository.PingRepository;
import com.ninuxgithub.dataserver.service.PingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;


@Service
public class PingServiceImpl implements PingService {

    @Autowired
    PingRepository pingRepository;

    @TxTransaction
    @Transactional
    @Override
    public Ping savePing() {
        Ping ping = pingRepository.save(new Ping(new Date()));
        return ping;
    }
}
