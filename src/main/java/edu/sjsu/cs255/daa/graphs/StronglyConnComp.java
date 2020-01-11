package edu.sjsu.cs255.daa.graphs;

import java.util.Arrays;
import java.util.Stack;

public class StronglyConnComp {

    private Graph graph;

    private enum EdgeType { BACK, TREE, FORWARD, CROSS, UNKNOWN }

    public StronglyConnComp(Graph graph) {
        this.graph = graph;
    }

    private boolean[] explored;
    private boolean[] processed;
    private int[] parent;
    private int[] entryTime;
    private int[] exitTime;
    private int[] low;
    private int[] scc;
    private int time;
    private int numScc;
    private boolean finished;
    private Stack<Integer> stack;

    private void initDfs() {
        time = 0;
        numScc = 0;
        finished = false;
        explored = new boolean[graph.numVertices()];
        processed = new boolean[graph.numVertices()];
        parent = new int[graph.numVertices()];
        entryTime = new int[graph.numVertices()];
        exitTime = new int[graph.numVertices()];
        low = new int[graph.numVertices()];
        scc = new int[graph.numVertices()];
        stack = new Stack<>();
        Arrays.fill(parent, -1);
        Arrays.fill(scc, -1);
        for (int i = 0; i < low.length; i++) {
            low[i] = i;
        }
    }

    public void strongComponents() {
        initDfs();
        for (int i = 0; i < graph.numVertices(); i++) {
            if (!explored[i]) {
                dfs(i);
            }
        }
        for (int i = 0; i < graph.numVertices(); i++) {
            System.out.println(i + " " + scc[i]);
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
            } else if (!processed[v] || graph.isDirected()) {
                processEdge(u, v);
            }
        }
        processVertexLate(u);
        time++;
        exitTime[u] = time;
        processed[u] = true;
    }

    private void processVertexEarly(int v) {
        stack.push(v);
    }

    private void processVertexLate(int v) {
        if (low[v] == v) {
            popComponent(v);
            return;
        }
        if (entryTime[low[v]] < entryTime[low[parent[v]]]) {
            low[parent[v]] = low[v];
        }
    }

    private void popComponent(int v) {
        numScc++;
        scc[v] =  numScc;
        int t = stack.pop();
        while (t != v) {
            scc[t] = numScc;
            t = stack.pop();
        }
    }

    private void processEdge(int u, int v) {
        EdgeType type = classifyEdge(u, v);
        if (type == EdgeType.BACK) {
            if (entryTime[v] < entryTime[low[u]]) {
                low[u] = v;
            }
        }
        if (type == EdgeType.CROSS) {
            if (scc[v] == -1) {
                if (entryTime[v] < entryTime[low[u]]) {
                    low[u] = v;
                }
            }
        }
    }

    private EdgeType classifyEdge(int u, int v) {
        if (parent[v] == u) {
            return EdgeType.TREE;
        }
        if (explored[v] && !processed[v]) {
            return EdgeType.BACK;
        }
        if (processed[v] && entryTime[v] > entryTime[u]) {
            return EdgeType.FORWARD;
        }
        if (processed[v] && entryTime[v] < entryTime[u]) {
            return EdgeType.CROSS;
        }
        return EdgeType.UNKNOWN;
    }
}
