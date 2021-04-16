package com.jia.neo4j;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.HashMap;


@SpringBootApplication
public class Neo4jApplication {

    public static void main(String[] args) {

        SpringApplication.run(Neo4jApplication.class, args);
        System.out.println("hahaha");
        System.out.println("${common.test}");
        System.out.println("${neo4j.min_connections}");

    }

}
