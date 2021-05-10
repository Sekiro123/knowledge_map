package com.jia.tag.dao;

import com.jia.common.entity.Article;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ArticleDao {
    public void save(Article article);
    public Article findOneArticle(String field);
    public void incTagTimes(String id);
}
