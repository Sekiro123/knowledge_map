package com.jia.modeling.service;

import com.jia.modeling.entity.entity;
import com.jia.modeling.entity.relation;

public interface relationService {
    void save(relation relation);
    relation findOne(String relation);
    Integer findTotals();

}
