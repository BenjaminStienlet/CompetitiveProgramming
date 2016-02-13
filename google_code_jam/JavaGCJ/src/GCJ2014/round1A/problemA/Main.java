package GCJ2014.round1A.problemA;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.BitSet;

public class Main {

    public static void main(String[] args) throws IOException {
        new Main();
    }

    BitSet[] outlets;
    BitSet[] devices;
    int n;
    int l;

    public Main() throws IOException {
        Scanner sc = new Scanner(System.in);
        BufferedWriter out = new BufferedWriter(new FileWriter("output.txt"));
        int test_cases = sc.nextInt();
        String temp;
        for (int test_case = 1; test_case <= test_cases; test_case++) {
            System.out.println(test_case);
            n = sc.nextInt();
            l = sc.nextInt();
            outlets = new BitSet[l];
            for (int i = 0; i < l; i++){
                outlets[i] = new BitSet(n);
            }
            for (int i = 0; i < n; i++){
                temp = sc.next();
                for (int j = 0; j < l; j++) {
                    if ('1' == temp.charAt(j)) {
                        outlets[j].set(i);
                    }
                }
            }

            devices = new BitSet[n];
            for (int i = 0; i < n; i++){
                temp = sc.next();
                devices[i] = new BitSet(l);
                for (int j = 0; j < l; j++) {
                    if ('1' == temp.charAt(j)) {
                        devices[i].set(j);
                    }
                }
            }
            out.write("Case #" + test_case + ": " + getResult() + "\n");
        }
        out.flush();
        out.close();
        sc.close();
    }

    private String getResult() {
        int result = func(0, 0);
        if (result == Integer.MAX_VALUE) {
            return "NOT POSSIBLE";
        }
        return "" + result;
    }

    private boolean quickCheck(int depth) {
        if (depth == 0) {
            return true;
        }
        HashSet<Integer> visited = new HashSet<Integer>();
        for (int i = 0; i < n; i++) {
            BitSet c = new BitSet(depth);
            for (int j = 0; j < depth; j++) {
                if (outlets[j].get(i)) {
                    c.set(j);
                }
            }
            boolean found = false;
            for (int j = 0; j < n; j++) {
                if (devices[j].get(0, depth).equals(c) && !visited.contains(j)){
                    visited.add(j);
                    found = true;
                    break;
                }
            }
            if (!found) {
                return false;
            }
        }
        return true;
    }

    private int func(int index, int nr) {
        if (!quickCheck(index)) {
            return Integer.MAX_VALUE;
        }
        if (index == l) {
            return nr;
        }
        outlets[index].flip(0, n);
        int flip = func(index+1, nr + 1);
        outlets[index].flip(0, n);
        int no_flip = func(index+1, nr);
        if (flip < no_flip) {
            return flip;
        }
        return no_flip;
    }
}