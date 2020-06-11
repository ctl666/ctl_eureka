package com.ctl.eureka_client_provider.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {
    @RequestMapping("/hello")
    public String sayHello(){
        System.out.println("hello");
        return "hello";
    }

}
