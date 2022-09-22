package com.example.lab2;

import com.example.lab2.model.ParallelConnectionNode;
import com.example.lab2.model.SequenceConnectionNode;
import com.example.lab2.model.SimpleNode;
import com.example.lab2.model.SystemNode;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class CalculateUtil {
    static double calcReliability(SystemNode node){
        if (node instanceof SimpleNode simpleNode){
            return calcReliability(simpleNode);
        }
        if (node instanceof SequenceConnectionNode sequenceConnectionNode){
            return calcReliability(sequenceConnectionNode);
        }
        if (node instanceof ParallelConnectionNode parallelConnectionNode){
            return calcReliability(parallelConnectionNode);
        }
        throw new IllegalArgumentException(String.format("Unknown type of node {%s}", node));
    }

    static double calcReliability(SimpleNode node){
        return node.getReliability();
    }

    static double calcReliability(SequenceConnectionNode node){
        return node.getNodes().stream().mapToDouble(CalculateUtil::calcReliability).reduce(1.0,(ac,v)->ac*v);
    }
    static double calcReliability(ParallelConnectionNode node){
        return 1-node.getNodes().stream().mapToDouble(n->1-calcReliability(n)).reduce(1.0,(ac,v)->ac*v);
    }
}
