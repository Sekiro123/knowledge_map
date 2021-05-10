package com.jia.modeling.service;

import com.jia.common.entity.entity;
import com.jia.common.entity.relation;

public interface relationService {
    void save(relation relation);
    String findOne(String field,String author);
    Integer findTotals();

}
