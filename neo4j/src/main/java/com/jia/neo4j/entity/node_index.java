package com.jia.neo4j.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;
import org.springframework.stereotype.Component;


@Data
@AllArgsConstructor
@ToString
public class node_index {
    private String name;
    private int category;
}
