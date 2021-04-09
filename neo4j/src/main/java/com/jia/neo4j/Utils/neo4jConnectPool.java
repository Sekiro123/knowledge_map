package com.jia.neo4j.Utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.LinkedBlockingQueue;

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

    private static int max_connections;

    @Value("${neo4j.max_connections}")
    public void setMax_connections(int max_connections){
        neo4jConnectPool.max_connections=max_connections;
    }
    private static volatile int current_connections;
    private static LinkedBlockingQueue<Connection> connections;
    @Value("${neo4j.max_connections}")
    public void setConnections(int max_connections){
        neo4jConnectPool.connections=new LinkedBlockingQueue<>(max_connections);
    }
    public neo4jConnectPool(){

        current_connections=0;
        while(current_connections<min_connections){
            try {
                connections.add(DriverManager.getConnection(uri,username,password));
                current_connections++;
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }
    public Connection getConnection()  {
        synchronized (neo4jConnectPool.class){
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
            Connection connection = null;

            return connections.poll();
        }

    }
    public void close(Connection connection) {

        try {
            connections.put(connection);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
