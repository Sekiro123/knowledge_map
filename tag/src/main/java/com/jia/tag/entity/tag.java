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

public class tag {
    private String account;
    private String subject;
    private String subject_properties;
    private String relation;
    private String object;
    private String object_properties;
    private String text;
    private Date time;
    private String field;
}
