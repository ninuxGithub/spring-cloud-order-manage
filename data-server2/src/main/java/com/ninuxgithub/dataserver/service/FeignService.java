package com.ninuxgithub.dataserver.service;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Component
@FeignClient(value = "auth-server")
public interface FeignService {

    @RequestMapping(value ="/api/savePing",method =  {RequestMethod.GET})
    public void savePing();


}
