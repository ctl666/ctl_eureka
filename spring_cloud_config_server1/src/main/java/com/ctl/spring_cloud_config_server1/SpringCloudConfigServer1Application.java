package com.ctl.spring_cloud_config_server1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableConfigServer
@EnableEurekaClient
public class SpringCloudConfigServer1Application {

    public static void main(String[] args) {
        SpringApplication.run(SpringCloudConfigServer1Application.class, args);
    }

}
