package com.jia.modeling.service;

import com.jia.modeling.dao.EntityDAO;
import com.jia.common.entity.entity;
//import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class entityServiceImpl implements entityService{
    @Override
    public Integer findTotals() {
        return entityDAO.findTotals();
    }

    @Autowired
    private EntityDAO entityDAO;

//    @Autowired
//    private RabbitTemplate rabbitTemplate;

    @Override
    public void save(entity entity) {
        entityDAO.save(entity);
    }

    @Override
    public entity findOne(String entity) {
        System.out.println("entity"+entity);
        try{
            com.jia.common.entity.entity one = entityDAO.findOne(entity);
            return one;
        }catch(NullPointerException e){
            return null;
        }
    }
//    @Override
//    public void incUser(String username){
//        rabbitTemplate.convertAndSend("submitLabel","user.incUser",username);
//    }

//    public static void main(String[] args) {
//        entityServiceImpl entityService = new entityServiceImpl();
//        System.out.println("entityService.findTotals() = " + entityService.findTotals());
//    }
}
