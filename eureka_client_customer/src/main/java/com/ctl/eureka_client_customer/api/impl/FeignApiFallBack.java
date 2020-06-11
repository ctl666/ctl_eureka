package com.ctl.eureka_client_customer.api.impl;

import com.ctl.eureka_client_customer.api.DemoApi;
import org.springframework.stereotype.Component;

//使用注解把这个对象交给spring管理
@Component
public class FeignApiFallBack implements DemoApi {
    /**
     * 调用服务失败时执行
     * @return
     */
    @Override
    public String sayHello() {
        return "调用服务失败！";
    }
}
