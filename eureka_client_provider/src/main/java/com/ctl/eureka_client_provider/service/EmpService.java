package com.ctl.eureka_client_provider.service;

import com.ctl.eureka_client_provider.entity.emp;
import com.ctl.eureka_client_provider.mapper.EmpMapper;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class EmpService {
    @Autowired
    private EmpMapper empMapper;
    @Cacheable(cacheNames = "emp")
    public emp getEmp(Integer id){
        emp empList = empMapper.getEmpList(id);
        return empList;
    }

    @RabbitListener(queues = "ctl.news")//监听消息队列
    public  void receive(emp emp){
        System.out.printf("收到消息"+emp);
    }

}
