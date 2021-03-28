package com.jia.modeling.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Accessors(chain=true)
public class entity {
    public String entity;
    @Value("nothing")
    private String properties;
    private String extra="nothing";
}
