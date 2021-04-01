package com.jia.neo4j.dao;

import org.neo4j.driver.v1.Driver;

public interface neo4jDAO {
    public String findByName(String name,String type);

}
