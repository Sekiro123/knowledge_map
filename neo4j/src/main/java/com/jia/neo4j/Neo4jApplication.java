package com.jia.neo4j;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.HashMap;


@SpringBootApplication
public class Neo4jApplication {

    public static void main(String[] args) {

        SpringApplication.run(Neo4jApplication.class, args);
        HashMap<Integer,Integer> map=new HashMap<>();
    }

}
