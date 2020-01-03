package edu.sjsu.cs255.daa.graphs;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BreadthFirstSearchTest {

    @BeforeEach
    void setUp() {
    }

    @Test
    void bfs() {
        int v1 = 13;
        int[][] edges1 = {{0, 1}, {0, 2}, {1, 3}, {1, 2}, {1, 4}, {2, 6}, {2, 7}, {2, 4},
                {3, 4}, {4, 5}, {6, 7}, {8, 9}, {10, 11}, {11, 12}};
        Graph graph = Graph.createGraph(v1, edges1);
        BreadthFirstSearch test = new BreadthFirstSearch(graph);
        BreadthFirstSearch.BfsResult result = test.bfs(0);
        System.out.println(result.getRoute());
        System.out.println(result);
        System.out.println(test.findPath(10, 12));
    }
}