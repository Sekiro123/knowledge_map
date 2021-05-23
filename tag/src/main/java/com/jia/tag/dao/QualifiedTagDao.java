package com.jia.tag.dao;

import com.jia.tag.entity.tag;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface QualifiedTagDao{
    public void save(tag tag);
    public List<tag> findAll();
    public List<tag> findByPage(int start,int length,String account,String field);
    public int count(String account,String field);
//    public void delete();
//    public void update();
}