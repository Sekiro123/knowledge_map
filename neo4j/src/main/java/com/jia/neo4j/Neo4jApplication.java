package com.jia.neo4j;

import org.neo4j.driver.AuthTokens;
import org.neo4j.driver.GraphDatabase;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.Driver;
import java.util.List;

@SpringBootApplication
public class Neo4jApplication {

    public static void main(String[] args) {

        SpringApplication.run(Neo4jApplication.class, args);
        public static void main( String[] args ) {

            Driver driver = (Driver) GraphDatabase.driver( "bolt://192.168.31.65:7687", AuthTokens.basic( "neo4j", "123456" ) );
            Session session = ((org.neo4j.driver.v1.Driver) driver).session();
            //session.run( "CREATE (a:Person {name: {name}, title: {title}})",parameters( "name", "Arthur001", "title", "King001" ) );

            try {
                StatementResult result = session.run( "MATCH (a:Subject3) WHERE a.ID3 = {name} " +
                        "RETURN a.ID3 AS name, a.title AS title",parameters( "name", 30001 ) );

                List<Record> records = result.list();
                System.out.println("result:"+records.size());

                for (Record recordItem : records) {

                    List<String> list=recordItem.keys();
                    for (String key:list){
                        System.out.println("key:"+key);
                        System.out.println("value:"+recordItem.get(key));
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                session.close();
                ((org.neo4j.driver.v1.Driver) driver).close();
            }

        }
    }

}
