package edu.sjsu.cs255.daa.graphs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BreadthFirstSearch {

    private Graph graph;

    public BreadthFirstSearch(Graph graph) {
        this.graph = graph;
    }

    public void processVertexEarly(int u) {
        System.out.println("processing: " + u);
    }

    public void processVertexLate(int u) {
        System.out.println("processed: " + u);

    }

    private int nEdges;
    public void processEdge(int u, int v) {
        System.out.println("edgeFound: (" + u + ", " + v + ")");
        nEdges++;
    }

    public BfsResult bfs(int src) {
        nEdges = 0;
        boolean[] discovered = new boolean[graph.numVertices()];
        boolean[] processed = new boolean[graph.numVertices()];
        int[] parent = new int[graph.numVertices()];
        int[] distance = new int[graph.numVertices()];
        List<Integer> route = new ArrayList<>();
        Queue<Integer> queue = new LinkedList<>();
        Arrays.fill(parent, -1);
        queue.add(src);
        discovered[src] = true;
        while (!queue.isEmpty()) {
            int u = queue.remove();
            processVertexEarly(u);
            processed[u] = true; // mark current node processed
            route.add(u);
            for (Graph.Edge edge : graph.getEdges(u)) {
                int v = edge.getV();
                if (!processed[v] || graph.isDirected()) { // process each edge exactly once
                    processEdge(u, v);
                }
                if (!discovered[v]) {
                    queue.add(v);
                    parent[v] = u;
                    distance[v] = distance[u] + 1; // update distance of v from src
                    discovered[v] = true; // mark v as discovered
                }
            }
            processVertexLate(u);
        }
        return new BfsResult(route, parent, distance, nEdges);
    }

    public List<Integer> findPath(int start, int end) {
        BfsResult result = bfs(start);
        int[] parent = result.getParent();
        List<Integer> path = new LinkedList<>();
        while (start != end) {
            if (parent[end] == -1) {
                path.clear();
                return path;
            }
            path.add(end);
            end = parent[end];
        }
        path.add(start);
        Collections.reverse(path);
        return path;
    }

    public static class BfsResult {
        private List<Integer> route;
        private int[] parent;
        private int[] distance;
        private int nEdges;

        public BfsResult(List<Integer> route, int[] parent, int[] distance, int nEdges) {
            this.route = route;
            this.parent = parent;
            this.distance = distance;
            this.nEdges = nEdges;
        }

        public int[] getParent() {
            return parent;
        }

        public int[] getDistance() {
            return distance;
        }

        public List<Integer> getRoute() {
            return route;
        }

        @Override
        public String toString() {
            StringBuilder res = new StringBuilder();
            res.append("route:").append(route).append("\n");
            res.append("N").append("\t").append("P").append("\t").append("D").append("\n");
            for (int i = 0; i < parent.length; i++) {
                res.append(i).append("\t").append(parent[i]).append("\t").append(distance[i]).append("\n");
            }
            res.append("edgesProcessed: ").append(nEdges);
            return res.toString();
        }
    }
}
