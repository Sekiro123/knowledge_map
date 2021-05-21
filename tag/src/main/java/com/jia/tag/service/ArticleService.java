package com.jia.tag.service;

import com.jia.common.entity.Article;
import com.jia.tag.dao.ArticleDao;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ArticleService {
    @Autowired
    private ArticleDao articleDao;
    @Autowired
    private RabbitTemplate rabbitTemplate;

    public String findOneArticle(String field,String account){
        Article article = articleDao.findOneArticle(field);
        articleDao.incTagTimes(article.getId());
        rabbitTemplate.convertAndSend("amq.direct","incNumArticles",account);
        return article.getContent();
    }
}
