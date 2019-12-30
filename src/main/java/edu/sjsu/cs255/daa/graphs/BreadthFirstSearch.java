package edu.sjsu.cs255.daa.graphs;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class BreadthFirstSearch {

    // return parent array
    public static int[] traverse(Graph graph, int src) {
        boolean[] discovered = new boolean[graph.getV()];
        int[] parent = new int[graph.getV()];
        Arrays.fill(parent, -1);
        bfs(graph, src, discovered, parent);
        return parent;
    }

    private static void bfs(Graph graph, int src, boolean[] discovered, int[] parent) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(src);
        discovered[src] = true;
        while (!queue.isEmpty()) {
            int u = queue.remove();
            for (int v : graph.getNeighboringNodes(u)) {
                if (!discovered[v]) {
                    queue.add(v);
                    discovered[v] = true;
                    parent[v] = u;
                }
            }
        }
    }
}