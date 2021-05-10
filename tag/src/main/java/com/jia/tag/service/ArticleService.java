package com.jia.tag.service;

import com.jia.common.entity.Article;
import com.jia.tag.dao.ArticleDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ArticleService {
    @Autowired
    private ArticleDao articleDao;

    public String findOneArticle(String field){
        Article article = articleDao.findOneArticle(field);
        articleDao.incTagTimes(article.getId());
        return article.getContent();
    }
}
