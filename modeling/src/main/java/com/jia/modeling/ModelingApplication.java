package com.jia.modeling;

import com.alibaba.fastjson.JSON;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


@EnableDiscoveryClient
@SpringBootApplication
public class ModelingApplication {

    public static void main(String[] args) {
        SpringApplication.run(ModelingApplication.class, args);
    }

}
