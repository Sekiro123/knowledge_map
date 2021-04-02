package com.jia.neo4j.Utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.ConcurrentLinkedQueue;

@Component
public class neo4jConnectPool{
    @Value("${neo4j.uri}")
    private String uri;
    @Value("${neo4j.username}")
    private String username;
    @Value("${neo4j.password}")
    private String password;
    @Value("${neo4j.min_connections}")
    private int min_connections;
    private static volatile int current_connections;
    private static ConcurrentLinkedQueue<Connection> connections = new ConcurrentLinkedQueue<>();
    public neo4jConnectPool(){
        current_connections=0;
    }
    public Connection getConnection(){
        while(current_connections<min_connections){
            try {
                connections.add(DriverManager.getConnection(uri,username,password));
                current_connections++;
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        if(connections.isEmpty()){
            try {
                connections.add(DriverManager.getConnection(uri,username,password));
                current_connections++;
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        Connection connection = connections.poll();

        return connection;
    }
    public void close(Connection connection){
        connections.add(connection);
    }

}
