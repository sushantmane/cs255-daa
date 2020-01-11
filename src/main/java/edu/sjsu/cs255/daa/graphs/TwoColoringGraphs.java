package edu.sjsu.cs255.daa.graphs;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class TwoColoringGraphs {

    enum Color { RED, BLUE, UNKNOWN }

    private Graph graph;

    public TwoColoringGraphs(Graph graph) {
        this.graph = graph;
    }

    public void twoColor() {
        initBfs();
        for (int i = 0; i <  graph.numVertices(); i++) {
            if (!discovered[i]) {
                if (!bfs(i)) {
                    return;
                }
            }
        }

        for (int i = 0; i < graph.numVertices(); i++) {
            System.out.println(i + " " + colors[i] + " p:" + parent[i]);
        }
    }

    private boolean bfs(int u) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(u);
        discovered[u] = true;
        colors[u] = Color.RED;
        while (!queue.isEmpty()) {
            u = queue.remove();
            processed[u] = true;
            for (Graph.Edge edge : graph.getEdges(u)) {
                int v = edge.getV();
                if (!discovered[v]) {
                    if (colors[u] == Color.RED) {
                        colors[v] = Color.BLUE;
                    } else if (colors[u] == Color.BLUE) {
                        colors[v] = Color.RED;
                    }
                    discovered[v] = true;
                    queue.add(v);
                    parent[v] = u;
                } else {
                    if (colors[u] == colors[v]) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    private void processEdge(int u, int v) {
        System.out.println("edge(" + u + ", " + v + ")");
    }

    private boolean[] discovered;
    private boolean[] processed;
    private int[] parent;
    private Color[] colors;

    private void initBfs() {
        discovered = new boolean[graph.numVertices()];
        processed = new boolean[graph.numVertices()];
        parent = new int[graph.numVertices()];
        colors = new Color[graph.numVertices()];
        Arrays.fill(colors, Color.UNKNOWN);
        Arrays.fill(parent, -1);
    }
}
