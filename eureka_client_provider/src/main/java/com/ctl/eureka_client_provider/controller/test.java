package com.ctl.eureka_client_provider.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class test {

    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Autowired
    private AmqpAdmin amqpAdmin;//用来管理exchange/queue/匹配规则binding

    @Test
    public void createExchange() {
        Exchange exchange = new DirectExchange("amqpadmin.exchange");
        this.amqpAdmin.declareExchange(exchange);
        System.out.printf("创建完成");
    }

    @Test
    public void createQueue(){
        Queue queue = new Queue("amqpadmin.queue",true);
        this.amqpAdmin.declareQueue(queue);
        System.out.printf("创建队列成功");
    }

    @Test
    public void createBindind(){
        Binding binding = new Binding("amqpadmin.queue",Binding.DestinationType.QUEUE,
                "amqpadmin.exchange","amqpadmin.queue",null);
        this.amqpAdmin.declareBinding(binding);
        System.out.printf("绑定成功");
    }


    @Test
    public void getMessage() {
        Object o = rabbitTemplate.receiveAndConvert("ctl.news");
        System.out.printf(o.toString());
    }

}
