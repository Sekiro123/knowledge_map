package com.jia.tag.service;

import com.jia.tag.entity.tag;

import java.util.List;

public interface tagService {
    public void save(tag tag);
    public List<tag> findAll();
    public List<tag> findByPage(int page,int limit,String account,String field);
    public int count(String account,String field);
    public int qualified(tag tag);
    public int unqualified(tag tag);
    public int quailifiedTagCount(String account,String field);
    public int unQualifiedTagCount(String account,String field);
}
