package com.ctl.eureka_client_customer.api;

import com.ctl.eureka_client_customer.api.impl.FeignApiFallBack;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

//api层调用euraka-provider的服务
@FeignClient(value = "dashboard-b",fallback = FeignApiFallBack.class)
public interface DemoApi {
     @RequestMapping("/hello")
     String sayHello();
}
