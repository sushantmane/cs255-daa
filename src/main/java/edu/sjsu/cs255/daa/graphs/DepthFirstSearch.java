package edu.sjsu.cs255.daa.graphs;

import java.util.Arrays;
import java.util.Stack;

public class DepthFirstSearch {

    private Graph graph;

    public DepthFirstSearch(Graph graph) {
        this.graph = graph;
    }

    public void processVertexEarly(int u) {
        System.out.println("processing:" + u);
    }

    public void processVertexLate(int u) {
        System.out.println("processed:" + u);
    }

    private int numEdges = 0;

    public void processEdge(int u, int v) {
        System.out.println("edgeFound: (" + u + ", " + v + ")");
        numEdges++;
    }

    public void dfsIterative(int src) {
        boolean[] processed = new boolean[graph.numVertices()];
        boolean[] discovered = new boolean[graph.numVertices()];
        int[] parent = new int[graph.numVertices()];
        Arrays.fill(parent, -1);
        int[] entryTime = new int[graph.numVertices()];
        int[] exitTime = new int[graph.numVertices()];
        int time = 0;
        Stack<Integer> stack = new Stack<>();
        stack.push(src);
        while (!stack.isEmpty()) {
            int u = stack.pop();
            if (discovered[u]) {
                if (!processed[u]) {
                    processed[u] = true;
                    time++;
                    exitTime[u] = time;
                    processVertexLate(u);
                }
                continue;
            }
            discovered[u] = true;
            stack.push(u); // push back on stack so that we can determine its exit time
            time++;
            entryTime[u] = time;
            processVertexEarly(u);
            for (Graph.Edge edge : graph.getEdges(u)) {
                int v = edge.getV();
                if (!discovered[v]) {
                    parent[v] = u;
                    processEdge(u, v);
                    stack.push(v);
                } else if (!processed[v] || graph.isDirected()) {
                    processEdge(u, v);
                }
            }
        }
    }

    public void dfsRecursive(int src) {

    }
}
