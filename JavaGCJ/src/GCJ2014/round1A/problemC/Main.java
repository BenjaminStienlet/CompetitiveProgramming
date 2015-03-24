package GCJ2014.round1A.problemC;

import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        new Main();
    }

    int n = 1000;
    Random gen = new Random();

    public Main() throws IOException, ClassNotFoundException {
        solve();
    }

    private void test() {
        int nr = 100;
        double count = 0;
        String answer;
        for (int i = 0; i < nr; i++) {
            answer = getResult(getGood());
            if (answer.equals("GOOD")) {
                count++;
            }
            answer = getResult(getBad());
            if (answer.equals("BAD")) {
                count++;
            }
        }
        System.out.println("Test accuracy: " + (100*count/(2*nr)) + "%");
    }

    private int getRandom(int a, int b) {
        return gen.nextInt(b-a) + a;
    }

    private Integer[] getGood() {
        Integer[] a = new Integer[n];
        for (int k = 0; k < n; k++) {
            a[k] = k;
        }
        int r, tmp;
        for (int k = 0; k < n; k++) {
            a[k] = k;
            r = getRandom(k, n);
            tmp = a[k];
            a[k] = a[r];
            a[r] = tmp;
        }
        return a;
    }

    private Integer[] getBad() {
        Integer[] a = new Integer[n];
        for (int k = 0; k < n; k++) {
            a[k] = k;
        }
        int r, tmp;
        for (int k = 0; k < n; k++) {
            a[k] = k;
            r = getRandom(0, n);
            tmp = a[k];
            a[k] = a[r];
            a[r] = tmp;
        }
        return a;
    }

    private void solve() throws IOException{
        System.out.println(">>> Input: ");
        Scanner sc = new Scanner(System.in);
        BufferedWriter out = new BufferedWriter(new FileWriter("output.txt"));
        int test_cases = sc.nextInt();
        int nr;
        Integer[] a;
        for (int test_case = 1; test_case <= test_cases; test_case++) {
            System.out.println("Solved " + test_case);
            nr = sc.nextInt();
            a = new Integer[nr];
            for (int i = 0; i < nr; i++) {
                a[i] = sc.nextInt();
            }
            out.write("Case #" + test_case + ": " + getResult(a) + "\n");
        }
        out.flush();
        out.close();
        sc.close();
    }

    private String getResult(Integer[] a) {
        double count = 0;
        for (int i=0; i<n; i++) {
            if (i <= a[i] ) {
                count++;
            }
        }
        double prob = count/n;
        if (prob <= 0.515) {
            return "GOOD";
        }
        return "BAD";
    }
}