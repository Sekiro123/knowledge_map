package com.jia.neo4j.service;

import org.json.JSONException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

public interface neo4jService {
    public String findOne(String name, HttpServletRequest request, HttpServletResponse response) throws SQLException, JSONException;
}
