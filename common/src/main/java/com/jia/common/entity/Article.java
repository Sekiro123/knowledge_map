package com.jia.common.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Article {
    private String id;
    private String name;
    private String Abstract;
    private String type1;
    private String type2;
    private String type3;
    private String type4;
    private String content;
    private int price;
    private String authorAccount;
    private String authorName;
    private Date publishDate;
    private int TagTimes;
    private int num_read;
}
