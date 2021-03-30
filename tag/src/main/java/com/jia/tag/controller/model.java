package com.jia.tag.controller;

public class model {
    public String object_type;
    public String predicate;
    public String subject_type;

    public model(String object_type, String predicate, String subject_type) {
        this.object_type = object_type;
        this.predicate = predicate;
        this.subject_type = subject_type;
    }

    @Override
    public String toString() {
        return "model{" +
                "object_type='" + object_type + '\'' +
                ", predicate='" + predicate + '\'' +
                ", subject_type='" + subject_type + '\'' +
                '}';
    }
}
