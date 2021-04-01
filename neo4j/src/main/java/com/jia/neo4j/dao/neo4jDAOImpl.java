package com.jia.neo4j.dao;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.sql.*;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Component
public class neo4jDAOImpl implements neo4jDAO, Serializable {
    @Value("${neo4j.uri}")
    private String uri;
    @Value("${neo4j.username}")
    private String username;
    @Value("${neo4j.password}")
    private String password;


    public Connection getNeo4jConnection(String uri, String username, String password) throws SQLException {
        Connection conn = DriverManager.getConnection(uri,username,password);
        return conn;
    }
    @Override
    public List<String> findByName(String name,String type) throws SQLException {
        ArrayList<String> ans = new ArrayList<>();
        System.out.println("uri = " + uri);
        Connection connection = getNeo4jConnection(uri, username, password);
        Statement statement = connection.createStatement();
        System.out.println("match p=(:" + type + "{name:'" + name + "'})-[]-() return p");
        ResultSet resultSet = statement.executeQuery("match p=()-[]->(:" + type + "{name:'" + name + "'}) return p");
        ResultSet resultSet2 = statement.executeQuery("match p=(:" + type + "{name:'" + name + "'})-[]->() return p");
        System.out.println("resultSet.toString() = " + resultSet.toString());
        while(resultSet.next()){
            System.out.println("resultSet.toString() = " + resultSet.toString());
            System.out.println("resultSet.getString(1) = " + resultSet.getString(1));
            ans.add(resultSet.getString(1));
        }
        while(resultSet2.next()){
            ans.add(resultSet2.getString(1));
        }
        connection.close();
        return ans;
    }
}
