package hw2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class task2 {

    private static class Edge {
        private final int to;
        private final long cost;

        private Edge(int to, long cost) {
            this.to = to;
            this.cost = cost;
        }
    }

    private static class Entry implements Comparable<Entry> {
        private final int vertex;
        private final long cost;

        private Entry(int vertex, long cost) {
            this.vertex = vertex;
            this.cost = cost;
        }

        @Override
        public int compareTo(Entry other) {
            return Long.compare(cost, other.cost);
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer input = forward(in);

        int n = Integer.parseInt(input.nextToken());
        int m = Integer.parseInt(input.nextToken());
        int start = Integer.parseInt(input.nextToken());
        int dest = Integer.parseInt(input.nextToken());

        List<List<Edge>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < m; i++) {
            input = forward(in);
            int from = Integer.parseInt(input.nextToken());
            int to = Integer.parseInt(input.nextToken());
            long cost = Long.parseLong(input.nextToken());
            graph.get(from).add(new Edge(to, cost));
        }

        long[] minCost = new long[n];
        Arrays.fill(minCost, Long.MAX_VALUE);
        minCost[start] = 0;

        PriorityQueue<Entry> queue = new PriorityQueue<>();
        queue.add(new Entry(start, 0));

        while (!queue.isEmpty()) {
            Entry current = queue.remove();
            if (current.cost != minCost[current.vertex]) {
                continue;
            }

            for (Edge edge : graph.get(current.vertex)) {
                if (current.cost <= Long.MAX_VALUE - edge.cost) {
                    long nextCost = current.cost + edge.cost;
                    if (nextCost < minCost[edge.to]) {
                        minCost[edge.to] = nextCost;
                        queue.add(new Entry(edge.to, nextCost));
                    }
                }
            }
        }

        if (minCost[dest] == Long.MAX_VALUE) {
            System.out.println("Чёт не доходит.");
        } else {
            System.out.println("cost=" + minCost[dest]);
        }
    }

    private static StringTokenizer forward(BufferedReader in) throws Exception {
        String line;
        do {
            line = in.readLine();
            if (line == null) {
                throw new Exception("stop");
            }
        } while (line.trim().isEmpty());
        return new StringTokenizer(line);
    }
}
