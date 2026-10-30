package hw2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;

public class task3 {
    private static class Edge {
        final int from;
        final int to;
        final long cost;

        Edge(int from, int to, long cost) {
            this.from = from;
            this.to = to;
            this.cost = cost;
        }
    }

    private static class isItACycle {
        private final int[] parent;
        private final int[] size;

        isItACycle(int n) {
            parent = new int[n];
            size = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
                size[i] = 1;
            }
        }

        int find(int x) {
            if (parent[x] != x) {
                parent[x] = find(parent[x]);
            }
            return parent[x];
        }

        boolean soIsItThen(int a, int b) {
            int rootX = find(a);
            int rootY = find(b);
            if (rootX == rootY) {
                return false;
            }
            if (size[rootX] < size[rootY]) {
                int temp = rootX;
                rootX = rootY;
                rootY = temp;
            }
            parent[rootY] = rootX;
            size[rootX] += size[rootY];
            return true;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        String line = readAll(in);
        StringTokenizer first = new StringTokenizer(line);
        int n = Integer.parseInt(first.nextToken());
        int m = Integer.parseInt(first.nextToken());

        List<Edge> edges = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            String edgeLine = readAll(in);
            StringTokenizer tokens = new StringTokenizer(edgeLine);
            if (tokens.countTokens() < 2) {
                continue;
            }

            int from = Integer.parseInt(tokens.nextToken());
            int to = Integer.parseInt(tokens.nextToken());
            if (from == to) {
                continue;
            }
            if (!tokens.hasMoreTokens()) {
                continue;
            }

            long cost = Long.parseLong(tokens.nextToken());
            edges.add(new Edge(from, to, cost));
        }

        edges.sort(Comparator.comparingLong(edge -> edge.cost));

        isItACycle iiac = new isItACycle(n);
        long totalCost = 0;
        int chosenEdges = 0;

        for (Edge edge : edges) {
            if (iiac.soIsItThen(edge.from, edge.to)) {
                totalCost += edge.cost;
                chosenEdges++;
                if (chosenEdges == n - 1) {
                    break;
                }
            }
        }

        if (chosenEdges < n - 1) {
            System.out.println("А чё оно не целое?");
        } else {
            System.out.println("cost=" + totalCost + " edges=" + chosenEdges);
        }
    }

    private static String readAll(BufferedReader in) throws Exception {
        String line;
        while ((line = in.readLine()) != null) {
            if (!line.trim().isEmpty()) {
                return line;
            }
        }
        throw new Exception();
    }
}
