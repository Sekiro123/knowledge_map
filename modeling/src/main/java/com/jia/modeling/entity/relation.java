package com.jia.modeling.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.springframework.stereotype.Component;

@Component
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain=true)
@ToString
public class relation {
    private String entity1;
    private String entity2;
    private String relation;
}
