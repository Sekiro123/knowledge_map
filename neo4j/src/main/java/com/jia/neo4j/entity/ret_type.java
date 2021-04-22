package com.jia.neo4j.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

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
        this.categories=new ArrayList<>();
        this.nodes=new ArrayList<>();
        this.links=new ArrayList<>();
    }
}
