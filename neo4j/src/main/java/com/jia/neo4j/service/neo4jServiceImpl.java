package com.jia.neo4j.service;

import com.jia.neo4j.dao.RedisUtil;
import com.jia.neo4j.dao.neo4jDAO;
import org.springframework.beans.factory.annotation.Autowired;
import com.jia.neo4j.dao.neo4jDAOImpl;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class neo4jServiceImpl implements neo4jService{
    @Autowired
    private neo4jDAOImpl neo4jDAO;

    @Autowired
    private RedisUtil redisUtil;

    @Override
    public String findOne(String name) {
//        neo4jDAOImpl neo4jDAO = new neo4jDAOImpl();
        Set<String> set = redisUtil.findSet(name);
        StringBuffer stringBuffer = new StringBuffer();
        System.out.println("set = " + set);
        for (String s : set) {
            System.out.println("for");
            String byName = neo4jDAO.findByName(name, s);
        }
        return null;
    }
}
