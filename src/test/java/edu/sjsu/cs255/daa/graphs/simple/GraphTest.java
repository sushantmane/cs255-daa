package edu.sjsu.cs255.daa.graphs.simple;

import org.junit.jupiter.api.Test;

class GraphTest {


    @Test
    public void test1() {
        int[][] edges = {{1, 2}, {1, 3}, {2, 4}, {2, 3}, {2, 5}, {3, 7}, {3, 8},
                {3, 5}, {4, 5}, {5, 6}, {7, 8}, {9, 10}, {11, 12}, {12, 13}};
        Graph graph = new Graph();
        for (int i = 1; i <= 13; i++) {
            graph.addVertex(i);
        }
        for (int[] edge : edges) {
            graph.addEdge(edge[0], edge[1]);
        }
        System.out.println(graph);
    }
}