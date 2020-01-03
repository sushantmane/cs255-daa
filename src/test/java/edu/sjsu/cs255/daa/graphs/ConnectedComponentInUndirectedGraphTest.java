package edu.sjsu.cs255.daa.graphs;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ConnectedComponentInUndirectedGraphTest {

    @Test
    void connectedComponents() {
        int v1 = 13;
        int[][] edges1 = {{0, 1}, {0, 2}, {1, 3}, {1, 2}, {1, 4}, {2, 6}, {2, 7}, {2, 4},
                {3, 4}, {4, 5}, {6, 7}, {8, 9}, {10, 11}, {11, 12}};
        Graph graph = Graph.createGraph(v1, edges1);
        ConnectedComponentInUndirectedGraph test = new ConnectedComponentInUndirectedGraph(graph);
        List<List<Integer>> result = test.connectedComponents();
        System.out.println(result);
    }
}