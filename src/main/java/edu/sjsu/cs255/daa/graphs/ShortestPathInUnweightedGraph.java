package edu.sjsu.cs255.daa.graphs;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class ShortestPathInUnweightedGraph {

    public static List<Integer> getPath(Graph graph, int src, int dest) {
        int[] parent = BreadthFirstSearch.traverse(graph, src);
        List<Integer> path = new LinkedList<>();
        while (dest != src) {
            path.add(dest);
            dest = parent[dest];
            if (dest == -1) {
                path.clear();
                return path;
            }
        }
        path.add(src);
        Collections.reverse(path);
        return path;
    }
}
