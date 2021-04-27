package com.jia.logs.dao;

import com.jia.common.entity.log;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface LogDAO {
    void insert(log log);
    void save(log log);
    List<log> findByAccount(String account);
}
