package com.jia.modeling.service;

import com.jia.modeling.dao.RelationDAO;
import com.jia.common.entity.entity;
import com.jia.common.entity.relation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class relationServiceImpl implements relationService{
    @Autowired
    private RelationDAO relationDAO;
    @Override
    public void save(relation relation) {
        relationDAO.save(relation);
    }

    @Override
    public relation findOne(String entity) {
        return null;
    }

    @Override
    public Integer findTotals() {
        return null;
    }
}
