package com.jia.modeling.service;

import com.jia.common.entity.entity;
import com.jia.common.entity.relation;

import java.util.List;

public interface relationService {
    void save(relation relation);
    String findOne(String field,String author);
    Integer findTotals();
    String update(List<relation> relations) ;
    String tagInfo(String field);
}
