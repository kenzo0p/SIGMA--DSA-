package BellmanFordsAlgo;

import java.util.ArrayList;
import java.util.Arrays;

//TODO : Watch again bellman fords and dijkstras algo 
public class BellmanFords {
    static class Edge {
        int src;
        int dest;
        int wt;

        public Edge(int src, int dest, int wt) {
            this.src = src;
            this.dest = dest;
            this.wt = wt;
        }
    }

    public static void createGraph(ArrayList<Edge> graph[]) {
        for (int i = 0; i < graph.length; i++) {
            graph[i] = new ArrayList<>();
        }
        graph[0].add(new Edge(0, 1, 2));

        graph[0].add(new Edge(0, 2, 4));

        graph[1].add(new Edge(1, 2, -4));

        graph[2].add(new Edge(2, 3, 2));

        graph[3].add(new Edge(3, 4, 4));

        graph[4].add(new Edge(4, 1, -1));

    }

    public static void bellmanFord(ArrayList<Edge> graph[], int src) { // o(VE)
        int dist[] = new int[graph.length];
        for (int i = 0; i < dist.length; i++) {
            if (i != src) {
                dist[i] = Integer.MAX_VALUE;
            }
        }
        // algo - o(v)
        int V = graph.length;
        for (int i = 0; i < V - 1; i++) {
            // edges - o(E)
            for (int j = 0; j < graph.length; j++) {
                for (int k = 0; k < graph[j].size(); k++) {
                    Edge e = graph[j].get(k);
                    // u ,v,wt
                    int u = e.src;
                    int v = e.dest;
                    int wt = e.wt;
                    if (dist[u] != Integer.MAX_VALUE && dist[u] + wt < dist[v]) {
                        dist[v] = dist[u] + wt;
                    }
                }
            }

        }

        for (int i = 0; i < dist.length; i++) {
            System.out.print(dist[i] + " ");
        }
        System.out.println();
    }

    // gfg soln and also neg cycle just do one more time relaxation
    // tc => o(E*V)
    public int[] bellmanFord(int V, int[][] edges, int src) {
        // code here
        int result[] = new int[V];
        int INF = (int) Math.pow(10, 8);
        Arrays.fill(result, INF);
        result[src] = 0;
        for (int count = 1; count <= V - 1; count++) {

            for (int edge[] : edges) {
                int u = edge[0];
                int v = edge[1];
                int wt = edge[2];
                if (result[u] != INF && result[u] + wt < result[v]) {
                    result[v] = result[u] + wt;
                }
            }
        }

        // Now detect neg cycle

        for (int edge[] : edges) {
            int u = edge[0];
            int v = edge[1];
            int wt = edge[2];
            if (result[u] != INF && result[u] + wt < result[v]) {
                return new int[] { -1 };
            }
        }

        return result;
    }     

    public static void main(String[] args) {
        int V = 5;
        @SuppressWarnings("unchecked")
        ArrayList<Edge> graph[] = new ArrayList[V];
        createGraph(graph);
        bellmanFord(graph, 0);
    }
}