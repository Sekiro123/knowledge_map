package com.jia.common.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class log {
    private String account;
    private Date time;
    private String operation;
    private String ip;
    private String extra_info;
}
