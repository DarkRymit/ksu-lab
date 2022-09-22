package com.example.lab2.model;

public sealed interface SystemNode permits ParallelConnectionNode, SequenceConnectionNode, SimpleNode {
    SystemNode getSelf();
}
