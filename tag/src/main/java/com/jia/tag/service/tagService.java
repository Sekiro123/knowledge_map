package com.jia.tag.service;

import com.jia.tag.entity.tag;

import java.util.List;

public interface tagService {
    public void save(tag tag);
    public List<tag> findAll();
}
