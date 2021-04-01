package com.jia.tag.dao;

import com.jia.tag.entity.tag;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TagDao{
    public void save(tag tag);
    public List<tag> findAll();
//    public void delete();
//    public void update();
}
