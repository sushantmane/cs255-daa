package edu.sjsu.cs255.daa.graphs;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ShortestPathInUnweightedGraphTest {

    @Test
    void getPath() {
        int[][] edges = {{0, 1}, {0, 2}, {1, 3}, {1, 2}, {1, 4},
                {2, 6}, {2, 7}, {2, 4}, {3, 4}, {4, 5}, {6, 7}};
        Graph graph = Graph.createGraph(8, edges);
        List<Integer> path = ShortestPathInUnweightedGraph.getPath(graph, 7, 5);
        List<Integer> res = Arrays.asList(7, 2, 4, 5);
        assertEquals(res, path);
    }
}