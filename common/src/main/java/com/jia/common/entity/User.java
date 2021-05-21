package com.jia.common.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class User {
    private String name;
    private String account;
    private String password;
    private String phoneNumber;
    private String payAccount;
    private String authorities="user";
    private Integer numArticles=0;
    private Integer numTag=0;
    private Integer numUsefulTag=0;
    private Integer numSentences=0;

    public User(String name, String account, String password, String phoneNumber, String payAccount) {
        this.name = name;
        this.account = account;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.payAccount = payAccount;
    }
}