package edu.sjsu.cs255.daa.graphs;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
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

    public DfsResult dfsIterative(int src) {
       initDfs();
        int time = 0;
        Stack<Integer> stack = new Stack<>();
        stack.push(src);
        List<Integer> route = new LinkedList<>();
        while (!stack.isEmpty()) {
            int u = stack.pop();
            if (explored[u]) {
                if (!processed[u]) {
                    processed[u] = true;
                    time++;
                    exitTime[u] = time;
                    processVertexLate(u);
                }
                continue;
            }
            explored[u] = true;
            stack.push(u); // push back on stack so that we can use it to determine its exit time
            route.add(u);
            time++;
            entryTime[u] = time;
            processVertexEarly(u);
            for (Graph.Edge edge : graph.getEdges(u)) {
                int v = edge.getV();
                if (!explored[v]) {
                    parent[v] = u;
                    processEdge(u, v);
                    stack.push(v);
                } else if (!processed[v] || graph.isDirected()) {
                    processEdge(u, v);
                }
            }
        }
        if (!graph.isDirected()) {
            numEdges = numEdges / 2;
        }
        DfsResult dfsResult = new DfsResult(route, parent, entryTime, exitTime, numEdges);
        return dfsResult;
    }

    private int time = 0;
    private int[] entryTime;
    private int[] exitTime;
    private int[] parent;
    private List<Integer> route;
    private boolean[] processed;
    private boolean[] explored;

    private void initDfs() {
        time = 0;
        entryTime = new int[graph.numVertices()];
        exitTime = new int[graph.numVertices()];
        parent = new int[graph.numVertices()];
        processed = new boolean[graph.numVertices()];
        explored = new boolean[graph.numVertices()];
        Arrays.fill(parent, -1);
        route = new LinkedList<>();
    }

    public DfsResult dfs(int src) {
        initDfs();
        dfsRecursive(src);
        DfsResult dfsResult = new DfsResult(route, parent, entryTime, exitTime, numEdges);
        return dfsResult;
    }

    private void dfsRecursive(int u) {
        time++;
        entryTime[u] = time;
        explored[u] = true;
        processVertexLate(u);
        route.add(u);
        for (Graph.Edge edge : graph.getEdges(u)) {
            int v = edge.getV();
            if (!explored[v]) {
                processEdge(u, v);
                parent[v] = u;
                dfsRecursive(v);
            }
            if (!processed[v] || graph.isDirected()) {
                processEdge(u, v);
            }
        }
        processVertexLate(u);
        time++;
        exitTime[u] = time;
        processed[u] = true;
    }

    public static class DfsResult {
        private List<Integer> route;
        private int[] parent;
        private int[] entryTime;
        private int[] exitTime;
        private int nEdges;

        public DfsResult(List<Integer> route, int[] parent, int[] entryTime, int[] exitTime, int nEdges) {
            this.route = route;
            this.parent = parent;
            this.entryTime = entryTime;
            this.exitTime = exitTime;
            this.nEdges = nEdges;
        }

        public int[] getParent() {
            return parent;
        }

        public int[] getEntryTime() {
            return entryTime;
        }

        public int[] getExitTime() {
            return exitTime;
        }

        public List<Integer> getRoute() {
            return route;
        }

        @Override
        public String toString() {
            StringBuilder res = new StringBuilder();
            res.append("route:").append(route).append("\n");
            res.append("N").append("\t").append("P").append("\t").append("En")
                    .append("\t").append("Ex").append("\n");
            for (int i = 0; i < parent.length; i++) {
                res.append(i).append("\t").append(parent[i]).append("\t").append(entryTime[i])
                        .append("\t").append(exitTime[i]).append("\n");
            }
            res.append("edgesProcessed: ").append(nEdges);
            return res.toString();
        }
    }
}
