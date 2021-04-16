package com.jia.modeling.service;

import com.jia.common.entity.entity;

public interface entityService {
    void save(entity entity);
    entity findOne(String entity);
    Integer findTotals();
//    public void incUser(String username);
}
