package edu.sjsu.cs255.daa.graphs;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Graph {

    private final List<Node> nodes = new ArrayList<>(); // adjacency info
    private final List<Edge> edges = new LinkedList<>(); // list of all edges
    private final int nVertices; // number of vertices in graph
    private int nEdges; // number of edges in graph
    private boolean directed; // is graph directed?

    public Graph(int nVertices, boolean directed) {
        this.nVertices = nVertices;
        this.directed = directed;
        for (int i = 0; i < nVertices; i++) {
            Node node = new Node(i);
            nodes.add(node);
        }
    }

    public Graph(int nVertices) {
        this(nVertices, false);
    }

    public int numVertices() {
        return nVertices;
    }

    public int numEdges() {
        return nEdges;
    }

    public List<Node> getAllNodes() {
        return nodes;
    }

    public boolean isDirected() {
        return directed;
    }

    public List<Edge> getAllEdges() {
        return edges;
    }

    public List<Edge> getEdges(int id) {
        return nodes.get(id).getEdges();
    }

    public List<Integer> neighbors(int id) {
        List<Integer> neighbors = new LinkedList<>();
        for (Edge edge : getEdges(id)) {
            neighbors.add(edge.getV());
        }
        return neighbors;
    }

    public void addEdge(int u, int v, int weight) {
        Edge edge = new Edge(u, v, weight);
        nodes.get(u).addEdge(edge);
        edges.add(edge);
        if (!directed) {
            nodes.get(v).addEdge(new Edge(v, u, weight));
        }
    }

    public void addEdge(int u, int v) {
        addEdge(u, v, 1);
    }

    public int getDegree(int u) {
        return nodes.get(u).getDegree();
    }

    public static class Node implements Comparable<Node> {
        private int id;
        private int degree; // out-degree of vertex
        private List<Edge> edges = new LinkedList<>(); // edge list

        private Node(int id) {
            this.id = id;
        }

        public int getId() {
            return id;
        }

        public int getDegree() {
            return degree;
        }

        public void incDegree() {
            degree++;
        }

        public List<Edge> getEdges() {
            return edges;
        }

        private void addEdge(Edge edge) {
            edges.add(edge);
            degree++;
        }

        @Override
        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof Node)) {
                return false;
            }
            Node node = (Node) o;
            return node.id == this.id;
        }

        @Override
        public int hashCode() {
            return Integer.hashCode(id);
        }

        @Override
        public String toString() {
            return String.valueOf(id);
        }

        @Override
        public int compareTo(Node node) {
            return Integer.compare(id, node.id);
        }
    }

    public static class Edge implements Comparable<Edge> {
        private final int u;
        private final int v;
        private final int weight;

        public Edge(int u, int v, int weight) {
            this.u = u;
            this.v = v;
            this.weight = weight;
        }

        public Edge(int u, int v) {
            this(u, v, 1);
        }

        public int getU() {
            return u;
        }

        public int getV() {
            return v;
        }

        public int getWeight() {
            return weight;
        }

        @Override
        public int compareTo(Edge edge) {
            int res = Integer.compare(this.u, edge.u);
            if (res == 0) {
                res = Integer.compare(this.v, edge.v);
            }
            return res;
        }

        @Override
        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof Edge)) {
                return false;
            }
            Edge edge = (Edge) o;
            return edge.u == this.u && edge.v == this.v;
        }

        @Override
        public int hashCode() {
            int res = Integer.hashCode(u);
            res = 31 * res + Integer.hashCode(v);
            return res;
        }

        @Override
        public String toString() {
            return "edge(" + u + ", " + v + ", " + weight + ")";
        }
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        for (Node node : nodes) {
            res.append(node.id).append(": ").append(neighbors(node.id)).append("\n");
        }
        return res.toString();
    }

    public static Graph createGraph(int nVertices, int[][] edges, boolean directed) {
        Graph graph = new Graph(nVertices, directed);
        for (int[] edge : edges) {
            graph.addEdge(edge[0], edge[1]);
        }
        return graph;
    }

    public static Graph createGraph(int nVertices, int[][] edges) {
        return createGraph(nVertices, edges, false);
    }
}
