package hw2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class task4 {
    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer input = readAllPartTwo(in);

        int n = Integer.parseInt(input.nextToken());
        int m = Integer.parseInt(input.nextToken());
        int x = Integer.parseInt(input.nextToken());

        List<List<Integer>> depends = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            depends.add(new ArrayList<>());
        }

        int[] incoming = new int[n];
        for (int i = 0; i < m; i++) {
            input = readAllPartTwo(in);
            int from = Integer.parseInt(input.nextToken());
            int to = Integer.parseInt(input.nextToken());
            depends.get(from).add(to);
            incoming[to]++;
        }

        PriorityQueue<Integer> ready = new PriorityQueue<>();
        for (int vers = 0; vers < n; vers++) {
            if (incoming[vers] == 0) {
                ready.add(vers);
            }
        }

        List<Integer> order = new ArrayList<>();
        while (!ready.isEmpty()) {
            int vers = ready.remove();
            order.add(vers);

            for (int dep : depends.get(vers)) {
                incoming[dep]--;
                if (incoming[dep] == 0) {
                    ready.add(dep);
                }
            }
        }

        if (order.size() != n) {
            throw new Exception("Цикл");
        }

        int xPosition = order.indexOf(x);
        if (xPosition < 0) {
            throw new Exception("Версия " + xPosition + " где-то не там.");
        }

        StringBuilder output = new StringBuilder("order=");
        for (int i = 0; i < order.size(); i++) {
            if (i > 0) {
                output.append(' ');
            }
            output.append(order.get(i));
        }
        output.append(" afterX=").append(n - xPosition - 1);
        System.out.println(output);
    }

    private static StringTokenizer readAllPartTwo(BufferedReader in) throws Exception {
        String line;
        while ((line = in.readLine()) != null) {
            if (!line.trim().isEmpty()) {
                return new StringTokenizer(line);
            }
        }
        throw new Exception();
    }
}
