package com.jia.tag.dao;


import com.jia.tag.entity.Sentence;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SentenceDao {
    public void save(Sentence sentence);
    public Sentence findOneSentence(String field);
    public void incTagTimes(Integer id);
}
