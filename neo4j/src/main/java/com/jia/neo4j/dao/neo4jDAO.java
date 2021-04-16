package com.jia.neo4j.dao;

import java.sql.SQLException;
import java.util.List;

public interface neo4jDAO {
    public List<String> findByName(String name, String type) throws SQLException;

}
