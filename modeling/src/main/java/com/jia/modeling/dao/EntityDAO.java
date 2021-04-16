package com.jia.modeling.dao;

import com.jia.common.entity.entity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface EntityDAO extends BaseDAO<entity,String>{
    entity findOne(String s) throws NullPointerException;
}
