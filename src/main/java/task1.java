package src.main.java;

public class task1 {
    public void sort(int[] arr) {
        int[] c = new int[6];

        for (int a : arr) {
            c[a] += 1;
        }
        int[] res = new int[arr.length];
        int i = 0;

        for (int a = 1; a <= 5; a++) {
            for (int j = 0; j < c[a]; j++) {
                i += 1;
                res[i] = a;
            }
        }
        arr = res;
    }
}