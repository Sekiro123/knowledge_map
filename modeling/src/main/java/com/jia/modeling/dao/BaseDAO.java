package com.jia.modeling.dao;

import com.jia.common.entity.relation;

import java.util.List;

public interface BaseDAO<T,K> {
    void save(T t);
    void update(T t);
//    void delete(K k);
    List<relation> findOne(String s1, String s2);
    List<T> findAll();
    Integer findTotals();
}
