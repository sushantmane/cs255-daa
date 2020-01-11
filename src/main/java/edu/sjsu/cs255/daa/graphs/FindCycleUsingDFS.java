package edu.sjsu.cs255.daa.graphs;

import java.util.Arrays;

public class FindCycleUsingDFS {

    private Graph graph;

    public FindCycleUsingDFS(Graph graph) {
        this.graph = graph;
    }

    public boolean findCycle() {
        initDfs();
        for (int i = 0; i < graph.numVertices(); i++) {
            if (!explored[i]) {
                if (dfs(i)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean dfs(int u) {
        explored[u] = true;
        for (Graph.Edge edge : graph.getEdges(u)) {
            int v = edge.getV();
            if (!explored[v]) {
                processEdge(u, v, EdgeType.TREE);
                parent[v] = u;
                if (dfs(v)) {
                    return true;
                }
            } else if (!processed[v] || graph.isDirected()) {
                if (processEdge(u, v, EdgeType.BACK)) {
                    return true;
                }
            }
        }
        processed[u] = true;
        return false;
    }

    enum EdgeType {TREE, BACK }

    private boolean processEdge(int u, int v, EdgeType edgeType) {
        System.out.println("Edge(" + u + ", " + v + ", " + edgeType + ")" );
        if (parent[u] != v && edgeType == EdgeType.BACK) {
            printCycle(v, u);
            return true;
        }
        return false;
    }

    private void printCycle(int start, int end) {
        System.out.println("PrintCycle: start: " + start + " end: " + end);
        while (start != end && end != -1) {
            System.out.println(end);
            end = parent[end];
        }
        System.out.println(start);
    }

    private boolean[] processed;
    private boolean[] explored;
    private int[] parent;

    private void initDfs() {
        processed = new boolean[graph.numVertices()];
        explored = new boolean[graph.numVertices()];
        parent = new int[graph.numVertices()];
        Arrays.fill(parent, -1);
    }
}
