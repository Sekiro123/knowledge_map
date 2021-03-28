package com.jia.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableFeignClients(value = {"com.jia.user"})
@SpringBootApplication
//@EnableGlobalMethodSecurity(securedEnabled = true,prePostEnabled = true)

public class GatewayApplication {

    public static void main(String[] args) throws InterruptedException {

        ConfigurableApplicationContext run = SpringApplication.run(GatewayApplication.class, args);

        System.out.println("run.getEnvironment().getProperty(\"info\") = " + run.getEnvironment().getProperty("info"));
    }

}
