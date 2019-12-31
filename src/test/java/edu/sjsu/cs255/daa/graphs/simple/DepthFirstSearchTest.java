package edu.sjsu.cs255.daa.graphs.simple;

import edu.sjsu.cs255.daa.graphs.simple.DepthFirstSearch;
import edu.sjsu.cs255.daa.graphs.simple.Graph;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DepthFirstSearchTest {

    @Test
    void traverseIterative_Test() {
        int[][] edges = {{0, 1}, {0, 2}, {1, 3}, {1, 2}, {1, 4},
                {2, 6}, {2, 7}, {2, 4}, {3, 4}, {4, 5}, {6, 7}};
        Graph graph = Graph.createGraph(8, edges);
        int[] parent = DepthFirstSearch.traverseIterative(graph, 0);
        int[] res = {-1, 3, 0, 4, 2, 4, 7, 2};
        assertArrayEquals(res, parent);
    }


    @Test
    void traverseRecursive_Test() {
        int[][] edges = {{0, 1}, {0, 2}, {1, 3}, {1, 2}, {1, 4},
                {2, 6}, {2, 7}, {2, 4}, {3, 4}, {4, 5}, {6, 7}};
        Graph graph = Graph.createGraph(8, edges);
        int[] parent = DepthFirstSearch.traverseRecursive(graph, 0);
        int[] res = {-1, 0, 4, 1, 3, 4, 2, 6};
        assertArrayEquals(res, parent);
    }
}