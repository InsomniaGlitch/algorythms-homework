package src.main.java;

import java.util.ArrayList;
import java.util.Collections;

public class task2 {
    public void sort(double[] arr) {
        int n = arr.length;
        ArrayList<ArrayList<Double>> bcks = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            bcks.add(new ArrayList<>());
        }
        for (double i : arr) {
            bcks.get((int)i).add(i);
        }
        int i = 0;
        for (ArrayList<Double> b : bcks) {
            Collections.sort(b);
            for (double d : b) {
                arr[i++] = d;
            }
        }
    }
}
