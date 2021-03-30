package com.jia.tag.service;

import com.jia.tag.dao.TagDao;
import com.jia.tag.entity.tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class tagServiceImpl implements tagService{
    @Autowired
    private TagDao tagDao;

    @Override
    public void save(tag tag) {
        tagDao.save(tag);
    }
}
