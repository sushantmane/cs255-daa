package edu.sjsu.cs255.daa.graphs;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class ConnectedComponentInUndirectedGraph {

    private Graph graph;

    public ConnectedComponentInUndirectedGraph(Graph graph) {
        this.graph = graph;
    }

    public List<List<Integer>> connectedComponents() {
        initBfs();
        List<List<Integer>> cc = new LinkedList<>();
        for (int i = 0; i < graph.numVertices(); i++) {
            if (!discovered[i]) {
                cc.add(bfs(i));
            }
        }
        return cc;
    }

    private boolean[] discovered;

    private void initBfs() {
        discovered = new boolean[graph.numVertices()];
    }

    private List<Integer> bfs(int u) {
        List<Integer> component = new LinkedList<>();
        Queue<Integer> queue = new LinkedList<>();
        queue.add(u);
        discovered[u] = true;
        while (!queue.isEmpty()) {
            u = queue.remove();
            component.add(u);
            for (Graph.Edge edge : graph.getEdges(u)) {
                int v = edge.getV();
                if (!discovered[v]) {
                    discovered[v] = true;
                    queue.add(v);
                }
            }
        }
        return component;
    }
}
