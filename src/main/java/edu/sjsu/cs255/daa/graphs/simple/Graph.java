package edu.sjsu.cs255.daa.graphs.simple;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public final class Graph {

    private final Map<Integer, List<Edge>> adjList = new HashMap<>();
    private Type type = Type.UNDIRECTED;
    private int V; // number of vertices/nodes
    private int E; // number of edges

    public Graph() {
    }

    public Graph(Type type) {
        this.type = type;
    }

    public void addVertex(int v) {
        adjList.put(v, new LinkedList<>());
        V++;
    }

    public int getV() {
        return V;
    }

    public int getE() {
        return E;
    }

    public void addEdge(int u, int v, int weight) {
        Edge edge = new Edge(u, v, weight);
        adjList.get(u).add(edge);
        E++;
        if (type == Type.UNDIRECTED) {
            edge = new Edge(v, u, weight);
            adjList.get(v).add(edge);
            E++;
        }
    }

    public List<Edge> getNeighbors(int u) {
        return adjList.get(u);
    }

    public void addEdge(int u, int v) {
        addEdge(u, v, 1);
    }

    public List<Integer> getNeighboringNodes(int u) {
        List<Integer> neighbors = new LinkedList<>();
        for (Edge v : adjList.get(u)) {
            neighbors.add(v.getV());
        }
        return neighbors;
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        for (int u : adjList.keySet()) {
            res.append(u).append(":");
            for (int v : getNeighboringNodes(u)) {
                res.append(" ").append(v);
            }
            res.append("\n");
        }
        return res.toString();
    }

    public enum Type { DIRECTED, UNDIRECTED }

    public static class Edge {
        /*
         *              w (weight/cost)
         * (start) u --------------------> v (end)
         */
        private int u; // start node
        private int v; // end node
        private int w; // weight (cost)

        public Edge(int u, int v, int w) {
            this.u = u;
            this.v = v;
            this.w = w;
        }

        public int getU() {
            return u;
        }

        public int getV() {
            return v;
        }

        public int getCost() {
            return w;
        }
    }

    public static Graph createGraph(int nVertices, int[][] edges) {
        return createGraph(nVertices, edges, Type.UNDIRECTED);
    }

    public static Graph createGraph(int nVertices, int[][] edges, Type type) {
        Graph graph = new Graph(type);
        for (int i = 0; i < nVertices; i++) {
            graph.addVertex(i);
        }
        for (int[] edge : edges) {
            graph.addEdge(edge[0], edge[1]);
        }
        return graph;
    }
}
