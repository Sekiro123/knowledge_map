package com.jia.neo4j.dao;

import com.jia.neo4j.Utils.neo4jConnectPool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.sql.*;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Component
public class neo4jDAOImpl implements neo4jDAO, Serializable {
    @Autowired
    private com.jia.neo4j.Utils.neo4jConnectPool neo4jConnectPool;

    @Override
    public List<String> findByName(String name,String type) throws SQLException {
        ArrayList<String> ans = new ArrayList<>();
        Connection connection = neo4jConnectPool.getConnection();
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
        neo4jConnectPool.close(connection);
        return ans;
    }
}
