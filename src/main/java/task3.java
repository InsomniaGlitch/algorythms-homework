package src.main.java;

public class task3 {
    public static void sort(int[] arr) {
        int max = 0;
        for (int a : arr) {
            max = a > max ? a : max;
        }
        int[] res = new int[arr.length];

        for (int i = 1; max/i > 0; i *= 10) {
            int[] c = new int[10];

            for (int a : arr) {
                int j = (int)((a/i) % 10);
                c[j] += 1;
            }
            for (int j = 1; j < 10; j++) {
                c[j] += c[j - 1];
            }
            for (int j = arr.length - 1; j >= 0; j--) {
                int a = arr[j];
                int num = (int)((a/i) % 10);
                c[num] -= 1;
                res[c[num]] = a;
            }
            arr = res;
        }
    }
}
