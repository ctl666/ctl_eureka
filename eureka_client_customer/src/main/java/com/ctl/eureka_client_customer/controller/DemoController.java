package com.ctl.eureka_client_customer.controller;

import com.ctl.eureka_client_customer.api.DemoApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class DemoController {
    @Autowired(required = false)
    private DemoApi demoApi;

    @RequestMapping("/aa")
    public String sayHello(){
        String s = this.demoApi.sayHello();
        System.out.println(s);
        return s ;
    }

    @RequestMapping("/getName")
    public  String getName(@RequestParam("name") String name){
     return name;
    }

}
