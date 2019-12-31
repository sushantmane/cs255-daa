package edu.sjsu.cs255.daa.graphs.simple;

import edu.sjsu.cs255.daa.graphs.simple.BreadthFirstSearch;
import edu.sjsu.cs255.daa.graphs.simple.Graph;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BreadthFirstSearchTest {

    @BeforeEach
    public void setup() {
    }

    @Test
    void bfsTest() {
        int[][] edges = {{0, 1}, {0, 2}, {1, 3}, {1, 2}, {1, 4},
                {2, 6}, {2, 7}, {2, 4}, {3, 4}, {4, 5}, {6, 7}};
        Graph graph = Graph.createGraph(8, edges);
        int[] parent = BreadthFirstSearch.traverse(graph, 0);
        int[] res = {-1, 0, 0, 1, 1, 4, 2, 2};
        assertArrayEquals(res, parent);
    }
}