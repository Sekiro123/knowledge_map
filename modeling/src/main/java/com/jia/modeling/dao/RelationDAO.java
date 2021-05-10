package com.jia.modeling.dao;

import com.jia.common.entity.relation;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RelationDAO extends BaseDAO<relation,String>{
    public List<String> findFields(String author);
    public void delete(relation relation);
}
