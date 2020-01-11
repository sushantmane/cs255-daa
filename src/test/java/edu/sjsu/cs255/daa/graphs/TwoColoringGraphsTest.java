package edu.sjsu.cs255.daa.graphs;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TwoColoringGraphsTest {

    @Test
    void twoColor() {
        int v4 = 6;
        int[][] edges4 = {{0, 1}, {0, 3}, {0, 4}, {1, 2}, {2, 4}, {2, 5}};
        Graph graph = Graph.createGraph(v4, edges4);
        TwoColoringGraphs test = new TwoColoringGraphs(graph);
        test.twoColor();
    }
}