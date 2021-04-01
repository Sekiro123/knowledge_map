package com.jia.neo4j.dao;

import org.neo4j.driver.v1.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component
public class neo4jDAOImpl implements neo4jDAO, Serializable {
    @Value("${neo4j.uri}")
    private String uri;
    @Value("${neo4j.username}")
    private String username;
    @Value("${neo4j.password}")
    private String password;


    public static Driver getDriver(String uri, String username, String password){
        return GraphDatabase.driver(uri, AuthTokens.basic(username,password));
    }
    @Override
    public String findByName(String name,String type) {
        System.out.println("uri = " + uri);
        Driver driver=getDriver(uri,username,password);
        Session session = driver.session();
        String ans = session.run("match p=(n:" + type + "{name:'" + name + "'})-[]->() return p").toString();
        System.out.println("run = " + ans);
        session.close();
        return ans;
    }
}
