package com.ctl.spring_cloud_config_client.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {
    @Value("${hello}")
    private String hello;

    @GetMapping("/aa")
    public String getHello(){
        return  this.hello;
    }
}
