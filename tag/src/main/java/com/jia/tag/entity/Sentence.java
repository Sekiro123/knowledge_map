package com.jia.tag.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Sentence {
    private int id;
    private String type1;
    private String type2;
    private String type3;
    private String type4;
    private String content;
    private int TagTimes;
}
