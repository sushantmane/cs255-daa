package edu.sjsu.cs255.daa.graphs;

import java.util.Arrays;

public class TopologicalSort {

    private Graph graph;

    public TopologicalSort(Graph graph) {
        this.graph = graph;
    }

    public int[] topSort() {
        initDfs();
        for (int i = 0; i < graph.numVertices(); i++) {
            if (!explored[i]) {
                dfs(i);
            }
        }
        return ordering;
    }

    private void dfs(int u) {
        if (finished) {
            return;
        }
        explored[u] = true;
        time++;
        entryTime[u] = time;
        processVertexEarly(u);
        for (Graph.Edge edge : graph.getEdges(u)) {
            int v = edge.getV();
            if (!explored[v]) {
                parent[v] = u;
                processEdge(u, v);
                dfs(v);
            } else if (!processed[v] || graph.isDirected()) {
                processEdge(u, v);
            }
            if (finished) {
                return;
            }
        }
        time++;
        exitTime[u] = time;
        processVertexLate(u);
        processed[u] = true;
    }

    private enum EdgeType { BACK, TREE, FORWARD, CROSS, UNKNOWN }

    private EdgeType classifyEdge(int u, int v) {
        if (parent[v] == u) {
            return EdgeType.TREE;
        }
        if (explored[v] && !processed[v]) {
            return EdgeType.BACK;
        }
        if (processed[v] && (entryTime[v] > entryTime[u])) {
            return EdgeType.FORWARD;
        }
        if (processed[v] && (entryTime[v] < entryTime[u])) {
            return EdgeType.CROSS;
        }
        return EdgeType.UNKNOWN;
    }

    private void processVertexEarly(int u) {
    }

    private void processVertexLate(int u) {
        ordering[idx] = u;
        idx--;
    }

    private void processEdge(int u, int v) {
        if (classifyEdge(u, v) == EdgeType.BACK) {
            System.out.println("Not a DAG - edge:" + u + ", " + v);
            finished = true;
        }
    }

    private boolean[] processed;
    private boolean[] explored;
    private int[] parent;
    private int[] entryTime;
    private int[] exitTime;
    private int[] ordering;
    private int idx;
    private int time;
    private boolean finished;

    private void initDfs() {
        time = 0;
        finished = false;
        idx = graph.numVertices() - 1;
        processed = new boolean[graph.numVertices()];
        explored = new boolean[graph.numVertices()];
        parent = new int[graph.numVertices()];
        entryTime = new int[graph.numVertices()];
        exitTime = new int[graph.numVertices()];
        ordering = new int[graph.numVertices()];
        Arrays.fill(parent, -1);
    }
}
