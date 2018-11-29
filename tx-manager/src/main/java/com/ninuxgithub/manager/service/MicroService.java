package com.ninuxgithub.manager.service;


import com.ninuxgithub.model.TxServer;
import com.ninuxgithub.model.TxState;

/**
 * create by lorne on 2017/11/11
 */
public interface MicroService {

    String  tmKey = "tx-manager";

    TxServer getServer();

    TxState getState();
}
