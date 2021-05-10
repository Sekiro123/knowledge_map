package com.jia.common.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain=true)
@ToString
public class relation {
    private String field;
    private String author;
    private String entity1;
    private String entity2;
    private String relation;
}
