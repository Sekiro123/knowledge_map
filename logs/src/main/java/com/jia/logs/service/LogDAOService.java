package com.jia.logs.service;

import com.jia.common.entity.log;
import com.jia.logs.dao.LogDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LogDAOService {
    @Autowired
    private LogDAO logDAO;

    public void insert(log log){
        logDAO.save(log);
    }
    public List<log> findByAccount(String account){
        return logDAO.findByAccount(account);
    }
}
