package edu.sjsu.cs255.daa.graphs;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ArticulationVerticesTest {

    @Test
    void findArticulationPoints() {
        int v5 = 8;
        int[][] edges5 = {{0, 1}, {0, 3}, {1, 2}, {1, 3}, {2, 4}, {3, 4}, {4, 1}, {5, 0}, {3, 5},
                {6, 7}, {6, 5}, {7, 5}, {7, 6}};
        Graph graph = Graph.createGraph(v5, edges5, true);
        ArticulationVertices obj = new ArticulationVertices(graph);
        obj.findArticulationPoints();
    }

    void findArticulationPoints_T2() {
        int v5 = 8;
        int[][] edges5 = {{0, 1}, {0, 3}, {0, 5}, {1, 2}, {1, 3}, {1, 4}, {2, 4}, {3, 4}, {3, 5},
                {5, 6}, {5, 7}, {6, 7}};
        Graph graph = Graph.createGraph(v5, edges5);
        ArticulationVertices obj = new ArticulationVertices(graph);
        obj.findArticulationPoints();
    }
}