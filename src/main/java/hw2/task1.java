package hw2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

public class task1 {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;

        while ((line = in.readLine()) != null && line.trim().isEmpty()) {
        }

        String[] first = line.trim().split("\\s+");
        int h = Integer.parseInt(first[0]);
        int w = Integer.parseInt(first[1]);

        char[][] map = new char[h][w];
        for (int i = 0; i < h; i++) {
            String row = in.readLine();
            for (int j = 0; j < w; j++) {
                map[i][j] = row.charAt(j);
            }
        }

        String[] path = in.readLine().trim().split("\\s+");
        int sr = Integer.parseInt(path[0]);
        int sc = Integer.parseInt(path[1]);
        int tr = Integer.parseInt(path[2]);
        int tc = Integer.parseInt(path[3]);

        int stkC = withStack(map, sr, sc, tr, tc);
        int queC = withQueue(map, sr, sc, tr, tc);

        if (stkC == -1 || queC == -1) {
            System.out.println("NO 0 0");
        } else {
            System.out.println("YES " + stkC + " " + queC);
        }
    }

    private static int withStack(char[][] map, int sr, int sc, int tr, int tc) {
        int rows = map.length;
        int cols = map[0].length;
        boolean[][] already = new boolean[rows][cols];
        Deque<int[]> stack = new ArrayDeque<>();

        stack.push(new int[] {sr, sc});
        already[sr][sc] = true;
        int vis = 1;
        boolean targetFound = false;

        while (!stack.isEmpty()) {
            int[] cur = stack.pop();
            int row = cur[0];
            int col = cur[1];

            if (row == tr && col == tc) {
                targetFound = true;
            }

            int[][] orient = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
            for (int[] step : orient) {
                int nextRow = row + step[0];
                int nextCol = col + step[1];

                if (nextRow >= 0 && nextRow < rows && nextCol >= 0 && nextCol < cols
                        && !already[nextRow][nextCol] && map[nextRow][nextCol] != '#') {
                    already[nextRow][nextCol] = true;
                    vis++;
                    stack.push(new int[] {nextRow, nextCol});
                }
            }
        }

        return targetFound ? vis : -1;
    }

    private static int withQueue(char[][] map, int sr, int sc, int tr, int tc) {
        int rows = map.length;
        int cols = map[0].length;
        boolean[][] already = new boolean[rows][cols];
        Deque<int[]> queue = new ArrayDeque<>();

        queue.addLast(new int[] {sr, sc});
        already[sr][sc] = true;
        int vis = 1;
        boolean targetFound = false;

        while (!queue.isEmpty()) {
            int[] cur = queue.removeFirst();
            int row = cur[0];
            int col = cur[1];

            if (row == tr && col == tc) {
                targetFound = true;
            }

            int[][] orient = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
            for (int[] step : orient) {
                int nextRow = row + step[0];
                int nextCol = col + step[1];

                if (nextRow >= 0 && nextRow < rows && nextCol >= 0 && nextCol < cols
                        && !already[nextRow][nextCol] && map[nextRow][nextCol] != '#') {
                    already[nextRow][nextCol] = true;
                    vis++;
                    queue.addLast(new int[] {nextRow, nextCol});
                }
            }
        }

        return targetFound ? vis : -1;
    }
}
