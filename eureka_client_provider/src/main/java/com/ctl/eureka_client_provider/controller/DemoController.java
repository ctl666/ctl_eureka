package com.ctl.eureka_client_provider.controller;

import com.ctl.eureka_client_provider.entity.emp;
import com.ctl.eureka_client_provider.mapper.EmpMapper;
import com.ctl.eureka_client_provider.service.EmpService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@RestController
public class DemoController {
    @Autowired
    EmpService empService;
    @Autowired
    RabbitTemplate rabbitTemplate;

    @RequestMapping("/hello")
    public String sayHello(){
        System.out.println("hello");
        return "hello";
    }
    @GetMapping("/aa")
    public String getAa(String aa){
        return aa;
    }

    @GetMapping("/getEmp")
    public emp getEmpList(Integer id){
        emp empList = empService.getEmp(id);
        return empList;
    }
    /*
    单播（点对点）
     */
    @GetMapping("/send")
    public void sendMessage(String aa){
        //message 需要自己构造一个；定义消息体内容和消息头
//        rabbitTemplate.send(exchange,routeKey,message);
        //只需要传入要发送的对象，自动序列化发送给rabbtimq
//        rabbitTemplate.convertAndSend(exchange,routeKey,message);
//        Map<String,Object> map=new HashMap<>();
//        map.put("msg","这是第一个消息");
//        map.put("data", Arrays.asList("h","e","l","l","w"));
//        emp empList = empService.getEmp(1);
        emp emp=new emp();
        emp.setId(1);
        emp.setName("猪八戒");
        emp.setDeptId(1);
        emp.setSalary(Float.valueOf("1000.0"));
        this.rabbitTemplate.convertAndSend("exchange.direct","ctl.news",
                emp);
    }
    @GetMapping("receive")
    public  void receive(){
        Object o = rabbitTemplate.receiveAndConvert("ctl.news");
        System.out.printf(o.toString());
    }



}
