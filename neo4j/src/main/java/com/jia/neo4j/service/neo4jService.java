package com.jia.neo4j.service;

import org.json.JSONException;

import java.sql.SQLException;

public interface neo4jService {
    public String findOne(String name) throws SQLException, JSONException;
}
