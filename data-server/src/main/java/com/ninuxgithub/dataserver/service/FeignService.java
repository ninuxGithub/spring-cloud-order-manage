package com.ninuxgithub.dataserver.service;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Component
@FeignClient(value = "data-server-two")
public interface FeignService {

    @RequestMapping(value ="/api/savePing",method =  {RequestMethod.GET})
    public boolean savePing();


}
