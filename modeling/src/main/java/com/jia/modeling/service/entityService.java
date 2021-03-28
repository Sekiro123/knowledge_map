package com.jia.modeling.service;

import com.jia.modeling.entity.entity;

public interface entityService {
    void save(entity entity);
    entity findOne(String entity);
    Integer findTotals();
    public void incUser(String username);
}
