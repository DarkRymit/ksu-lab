package com.example.lab2.model;

import lombok.Value;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Value
public class ParallelConnectionNode implements SystemNode {
    List<SystemNode> nodes;

    public static ParallelConnectionNode of(SystemNode... nodes) {
        return new ParallelConnectionNode(List.of(nodes));
    }

    public static ParallelConnectionNode of(double... nodesReliability) {
        return new ParallelConnectionNode(
                Arrays.stream(nodesReliability).mapToObj(SimpleNode::of).map(SystemNode.class::cast).toList());
    }

    @Override
    public ParallelConnectionNode getSelf() {
        return this;
    }
}
