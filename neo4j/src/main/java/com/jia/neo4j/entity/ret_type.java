package com.jia.neo4j.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@ToString
public class ret_type {
    private List<node> categories;
    private List<node_index> nodes;
    private List<relation> links;
    public ret_type(){
        categories=new ArrayList<>();
        nodes=new ArrayList<>();
        links=new ArrayList<>();
    }
    public void clear(){
        categories=null;
        nodes=null;
        links=null;
    }
}
