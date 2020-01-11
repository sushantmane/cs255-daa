package edu.sjsu.cs255.daa.graphs;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class TopologicalSortTest {

    @Test
    void topSort() {
        int v5 = 8;
        int[][] edges5 = {{0, 1}, {0, 3}, {1, 2}, {1, 3}, {2, 4}, {3, 4}, {5, 0}, {5, 3},
                {6, 7}, {6, 5}, {7, 5}};
        Graph graph = Graph.createGraph(v5, edges5, true);
        TopologicalSort obj = new TopologicalSort(graph);
        int[] res = obj.topSort();
        Arrays.stream(res).forEach(System.out::println);
    }
}