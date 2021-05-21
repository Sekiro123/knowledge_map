package com.jia.tag.service;

import com.jia.tag.dao.RedisUtil;
import com.jia.tag.dao.TagDao;
import com.jia.tag.entity.tag;
import org.neo4j.driver.v1.AuthTokens;
import org.neo4j.driver.v1.Driver;
import org.neo4j.driver.v1.GraphDatabase;
import org.neo4j.driver.v1.Session;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class tagServiceImpl implements tagService{
    @Autowired
    private TagDao tagDao;

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Override
    public void save(tag tag) {
        tagDao.save(tag);
        System.out.println("send inc"+ tag.getAccount()+"'s tagNum");
        rabbitTemplate.convertAndSend("amq.direct","incUserTagNum",tag.getAccount());
    }

    @Override
    public List<tag> findAll() {
        return tagDao.findAll();

    }
    public void insertOneRelation(tag tag)
    {


    }
    public String updateNeo4j(){
        List<tag> all = tagDao.findAll();
        Driver driver = GraphDatabase.driver("bolt://192.168.31.65:7687", AuthTokens.basic("neo4j", "123456"));
        Session session = driver.session();
        for(int i=0;i<all.size();i++){
            tag tag = all.get(i);
            tag.setSubject(tag.getSubject().replaceAll("·","_"));
            tag.setObject(tag.getObject().replaceAll("·","_"));
            tag.setSubject_properties(tag.getSubject_properties().replaceAll("·","_"));
            tag.setObject_properties(tag.getObject_properties().replaceAll("·","_"));
            tag.setRelation(tag.getRelation().replaceAll("·","_"));

//            System.out.println("tag = " + tag);
//            System.out.println(redisUtil.findSet(tag.getSubject()));
            session.run("merge(:"+tag.getSubject_properties()+"{name:'"+tag.getSubject()+"',type:'"+tag.getSubject_properties()+"'})");
            redisUtil.addSet(tag.getSubject(),tag.getSubject_properties());
            session.run("merge(:"+tag.getObject_properties()+"{name:'"+tag.getObject()+"',type:'"+tag.getObject_properties()+"'})");
            System.out.println("\"merge(:\"+tag.getObject_properties()+\"{name:'\"+tag.getObject()+\"',type:'\"+tag.getObject_properties()+\"'})\" = " + "merge(:" + tag.getObject_properties() + "{name:'" + tag.getObject() + "',type:'" + tag.getObject_properties() + "'})");
            redisUtil.addSet(tag.getObject(),tag.getObject_properties());
            session.run("match(a:"+tag.getSubject_properties()+"{name:'"+tag.getSubject()+"'}),(b:"+tag.getObject_properties()
                    +"{name:'"+tag.getObject()+"'}) create (a)-[r:"+tag.getRelation()+"{relation_name:'"+tag.getRelation()+"'}]->(b)");
            System.out.println("match(a:" + tag.getSubject_properties() + "{name:'" + tag.getSubject() + "'}),(b:" + tag.getObject_properties()
                    + "{name:'" + tag.getObject() + "'}) create (a)-[r:" + tag.getRelation() + "{relation_name:'" + tag.getRelation() + "'}]->(b)");
        }
        return "success";
    }
//    public String updateRedis(){
//        List<tag> all = tagDao.findAll();
//
//    }

}
