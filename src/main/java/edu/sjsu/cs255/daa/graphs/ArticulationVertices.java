package edu.sjsu.cs255.daa.graphs;

import java.util.Arrays;

public class ArticulationVertices {

    private Graph graph;
    private boolean[] processed;
    private boolean[] explored;
    private int[] parent;
    private int[] entryTime;
    private int[] exitTime;
    private int[] reachableAncestor;
    private int[] treeOutDegree;
    private int time;

    public ArticulationVertices(Graph graph) {
        this.graph = graph;
    }

    public void findArticulationPoints() {
        initDfs();
        for (int i = 0; i < graph.numVertices(); i++) {
            if (!explored[i]) {
                dfs(i);
            }
        }
    }

    private void dfs(int u) {
        time++;
        entryTime[u] = time;
        explored[u] = true;
        processVertexEarly(u);
        for (Graph.Edge edge : graph.getEdges(u)) {
            int v = edge.getV();
            if (!explored[v]) {
                parent[v] = u;
                processEdge(u, v);
                dfs(v);
            }
            if (!processed[v] || graph.isDirected()) {
                processEdge(u, v);
            }
        }
        time++;
        exitTime[u] = time;
        processVertexLate(u);
        processed[u] = true;
    }

    private void processVertexEarly(int v) {
        reachableAncestor[v] = v;
    }

    private void processVertexLate(int v) {
        // root articulation vertex test
        if (parent[v] == -1) {
            if (treeOutDegree[v] > 1) {
                System.out.println("Root articulation vertex: " + v);
            }
            return;
        }
        // parent articulation vertex test
        if (reachableAncestor[v] == parent[v] && parent[parent[v]] != -1) {
            System.out.println(v + " Parent articulation vertex: " + parent[v]);
        }
        // bridge articulation vertex test
        if (reachableAncestor[v] == v) {
            System.out.println("Bridge articulation vertex (p): " + parent[v]);
            if (treeOutDegree[v] > 0) {
                System.out.println(v+ " Bridge articulation vertex (c): " + v);
            }
        }
        if (entryTime[reachableAncestor[v]] < entryTime[reachableAncestor[parent[v]]]) {
            reachableAncestor[parent[v]] = reachableAncestor[v];
        }
    }

    private void processEdge(int u, int v) {
        EdgeType type = classifyEdge(u, v);
        if (type == EdgeType.TREE) {
            treeOutDegree[u] = treeOutDegree[u] + 1;
        }
        if (type == EdgeType.BACK && parent[u] != v) {
            if (entryTime[v] < entryTime[reachableAncestor[u]]) {
                reachableAncestor[u] = v;
            }
        }
    }

    private void initDfs() {
        processed = new boolean[graph.numVertices()];
        explored = new boolean[graph.numVertices()];
        parent = new int[graph.numVertices()];
        entryTime = new int[graph.numVertices()];
        exitTime = new int[graph.numVertices()];
        reachableAncestor = new int[graph.numVertices()];
        treeOutDegree = new int[graph.numVertices()];
        time = 0;
        Arrays.fill(parent, -1);
    }

    enum EdgeType { TREE, BACK, FORWARD, CROSS, UNKNOWN }

    private EdgeType classifyEdge(int u, int v) {
        if (parent[v] == u) {
            return EdgeType.TREE;
        }
        if (explored[v] && !processed[v]) {
            return EdgeType.BACK;
        }
        if (processed[v] && entryTime[v] < entryTime[u]) {
            return EdgeType.CROSS;
        }
        if (processed[v] && entryTime[v] > entryTime[u]) {
            return EdgeType.FORWARD;
        }
        return EdgeType.UNKNOWN;
    }
}
