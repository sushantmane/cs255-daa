package edu.sjsu.cs255.daa.graphs;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.*;

class GraphTest {

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    public void createGraph_Test1() {
        int v1 = 13;
        int[][] edges1 = {{0, 1}, {0, 2}, {1, 3}, {1, 2}, {1, 4}, {2, 6}, {2, 7}, {2, 4},
                {3, 4}, {4, 5}, {6, 7}, {8, 9}, {10, 11}, {11, 12}};
        Graph graph = Graph.createGraph(v1, edges1);
        System.out.println(graph);
    }

    @Test
    public void createGraph_Test2() {
        int v2 = 5;
        int[][] edges2 = {{0, 1}, {0, 2}, {0, 3}, {0, 4}, {1, 2}, {1, 3}, {1, 4}, {2, 3},
                {2, 4}, {3, 4}};
        Graph graph = Graph.createGraph(v2, edges2);
        System.out.println(graph);
    }

    @Test
    public void createGraph_Test3() {
        int v3 = 6;
        int[][] edges3 = {{0, 1}, {0, 4}, {0, 5}, {1, 2}, {1, 3}, {1, 4}, {1, 5}, {2, 3}, {2, 5},
                {3, 4}, {3, 5}, {4, 5}};
        Graph graph = Graph.createGraph(v3, edges3);
        System.out.println(graph);
    }

    @Test
    public void createGraph_Test4() {
        int v4 = 6;
        int[][] edges4 = {{0, 1}, {0, 3}, {0, 4}, {1, 2}, {2, 4}, {2, 5}, {3, 4}, {4, 5}};
        Graph graph = Graph.createGraph(v4, edges4);
        System.out.println(graph);
    }

    @Test
    public void createGraph_Test5() {
        int v5 = 8;
        int[][] edges5 = {{0, 1}, {0, 3}, {1, 2}, {1, 3}, {2, 4}, {3, 4}, {4, 1}, {5, 0}, {5, 3},
                {6, 7}, {6, 5}, {7, 5}, {7, 6}};
        Graph graph = Graph.createGraph(v5, edges5, true);
        System.out.println(graph);
    }
}