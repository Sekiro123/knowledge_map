package com.jia.modeling.dao;

import java.util.List;

public interface BaseDAO<T,K> {
    void save(T t);
    void update(T t);
    void delete(K k);
    T findOne(K k);
    List<T> findAll();
    Integer findTotals();
}
