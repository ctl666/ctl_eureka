package com.ctl.euraka_demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class EurakaDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(EurakaDemoApplication.class, args);
    }

}
