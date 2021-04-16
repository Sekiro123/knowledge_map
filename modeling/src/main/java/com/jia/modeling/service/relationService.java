package com.jia.modeling.service;

import com.jia.common.entity.entity;
import com.jia.common.entity.relation;

public interface relationService {
    void save(relation relation);
    relation findOne(String relation);
    Integer findTotals();

}
