package com.ninuxgithub.dataserver.api;

import com.ninuxgithub.dataserver.service.PingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PingController {

    @Autowired
    private PingService pingService;

    @RequestMapping(value = "/api/savePing", method = RequestMethod.GET)
    public boolean savePing() {
        return pingService.savePing() !=null;
    }
}
