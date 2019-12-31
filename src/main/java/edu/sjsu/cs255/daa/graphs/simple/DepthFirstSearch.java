package edu.sjsu.cs255.daa.graphs.simple;

import java.util.Arrays;
import java.util.Stack;

public class DepthFirstSearch {

    // returns parent array
    public static int[] traverseIterative(Graph graph, int src) {
        boolean[] explored = new boolean[graph.getV()];
        int[] parent = new int[graph.getV()];
        Arrays.fill(parent, -1);
        Stack<Integer> stack = new Stack<>();
        stack.push(src);
        while (!stack.isEmpty()) {
            int u = stack.pop();
            if (explored[u]) {
                continue;
            }
            explored[u] = true;
            System.out.println(u);
            for (int v : graph.getNeighboringNodes(u)) {
                if (!explored[v]) {
                    stack.push(v);
                    parent[v] = u;
                }
            }
        }
        return parent;
    }

    // returns parent array
    public static int[] traverseRecursive(Graph graph, int src) {
        boolean[] explored = new boolean[graph.getV()];
        int[] parent = new int[graph.getV()];
        Arrays.fill(parent, -1);
        traverseRecursive(graph, src, explored, parent);
        return parent;
    }

    private static void traverseRecursive(Graph graph, int u, boolean[] explored, int[] parent) {
        if (explored[u]) {
            return;
        }
        explored[u] = true;
        for (int v : graph.getNeighboringNodes(u)) {
            if (explored[v]) {
                continue;
            }
            traverseRecursive(graph, v, explored, parent);
            parent[v] = u;
        }
    }
}
