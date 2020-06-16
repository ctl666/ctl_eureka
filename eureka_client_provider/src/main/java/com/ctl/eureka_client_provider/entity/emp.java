package com.ctl.eureka_client_provider.entity;

import com.netflix.discovery.provider.Serializer;
import lombok.Data;

import java.io.Serializable;

@Data
public class emp implements Serializable {

    private Integer id;
    private String name;
    private Integer deptId;
    private Float salary;
}
