package com.example.lab2.model;

import lombok.Value;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Value
public class SequenceConnectionNode implements SystemNode {

    List<SystemNode> nodes;

    public static SequenceConnectionNode of(SystemNode... nodes) {
        return new SequenceConnectionNode(List.of(nodes));
    }

    public static SequenceConnectionNode of(double... nodesReliability) {
        return new SequenceConnectionNode(
                Arrays.stream(nodesReliability).mapToObj(SimpleNode::of).map(SystemNode.class::cast).toList());
    }

    @Override
    public SequenceConnectionNode getSelf() {
        return this;
    }
}
