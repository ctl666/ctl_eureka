package com.ctl.spring_cloud_gateway_server.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FallbackController {
    @RequestMapping("/fallback")
    public String fallback(){
        return "gateway服务转发服务失败";
    }
}
