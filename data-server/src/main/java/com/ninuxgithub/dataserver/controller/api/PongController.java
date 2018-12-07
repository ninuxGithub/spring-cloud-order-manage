package com.ninuxgithub.dataserver.controller.api;


import com.ninuxgithub.dataserver.service.PongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PongController {


    @Autowired
    PongService pongService;


    @RequestMapping(value ="/api/savePong", method = RequestMethod.GET)
    public void savePong(){
        pongService.savePingPong();
    }
}
