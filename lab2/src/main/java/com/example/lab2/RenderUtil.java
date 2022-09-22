package com.example.lab2;

import com.example.lab2.model.ParallelConnectionNode;
import com.example.lab2.model.SequenceConnectionNode;
import com.example.lab2.model.SimpleNode;
import com.example.lab2.model.SystemNode;
import lombok.extern.slf4j.Slf4j;

import java.util.stream.Collectors;

@Slf4j
public abstract class RenderUtil {

    public static String render(SystemNode node, String prefix) {
        if (node instanceof SimpleNode simpleNode) {
            return render(simpleNode, prefix);
        }
        if (node instanceof SequenceConnectionNode sequenceConnectionNode) {
            return render(sequenceConnectionNode, prefix);
        }
        if (node instanceof ParallelConnectionNode parallelConnectionNode) {
            return render(parallelConnectionNode, prefix);
        }
        throw new IllegalArgumentException(String.format("Unknown type of node {%s}", node));
    }

    public static String render(SimpleNode node, String prefix) {
        return String.format("%s%s%n", getMyPrefix(prefix), node);
    }

    public static String render(SequenceConnectionNode node, String prefix) {
        return String.format("%s%s%n", getMyPrefix(prefix), node.getClass().getSimpleName()) + node.getNodes()
                .stream()
                .map(n -> render(n, getPrefixForNext(prefix)))
                .collect(Collectors.joining());
    }

    public static String render(ParallelConnectionNode node, String prefix) {
        return String.format("%s%s%n", getMyPrefix(prefix), node.getClass().getSimpleName()) + node.getNodes()
                .stream()
                .map(n -> render(n, getPrefixForNext(prefix)))
                .collect(Collectors.joining());
    }

    private static String getMyPrefix(String prefix) {
        return prefix.substring(0,prefix.length()-1);
    }

    private static String getPrefixForNext(String prefix) {
        return prefix + String.valueOf(prefix.charAt(prefix.length() - 1)).repeat(4);
    }
}
