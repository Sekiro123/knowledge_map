package com.jia.tag;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan
public class TagApplication {

    public static void main(String[] args) {
        SpringApplication.run(TagApplication.class, args);
    }

}
