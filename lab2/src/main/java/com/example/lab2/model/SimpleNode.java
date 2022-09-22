package com.example.lab2.model;

import lombok.Value;

@Value
public class SimpleNode implements SystemNode {

    double reliability;

    @Override
    public SimpleNode getSelf() {
        return this;
    }
    public static SimpleNode of(double reliability){
        return new SimpleNode(reliability);
    }
}
