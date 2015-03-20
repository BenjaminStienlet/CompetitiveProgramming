package GCJ2014.round1A.problemB;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        new Main();
    }

    int n;
    BitSet[] edges;

    HashMap<List<Integer>, Integer> map;

    public Main() throws IOException {
        solve();
    }

    private void solve() throws IOException{
        Scanner sc = new Scanner(System.in);
        BufferedWriter out = new BufferedWriter(new FileWriter("output.txt"));
        int test_cases = sc.nextInt();
        for (int test_case = 1; test_case <= test_cases; test_case++) {
            System.out.println(test_case);
            n = sc.nextInt();
            edges = new BitSet[n];
            for (int i = 0; i < n; i++) {
                edges[i] = new BitSet(n);
            }
            int begin, end;
            for (int i = 0; i < n - 1; i++) {
                begin = sc.nextInt();
                end = sc.nextInt();
                edges[begin - 1].set(end - 1);
                edges[end - 1].set(begin - 1);
            }
            out.write("Case #" + test_case + ": " + getResult() + "\n");
        }
        out.flush();
        out.close();
        sc.close();
    }

    private String getResult() {
        int min = Integer.MAX_VALUE;
        int children;
        for (int i = 0; i < n; i++) {
//            System.out.println("==========\nRoot " + i);
            children = getChildren(i, -1);
            if (n-children < min) {
                min = n-children;
            }
        }
        return "" + min;
    }

    private int getChildren(int node, int parent) {
        ArrayList<Integer> list = new ArrayList<Integer>();
        for (int j=0; j < n; j++) {
            if (edges[node].get(j) && j != parent) {
                list.add(j);
            }
        }

        if (list.size() < 2) {
//            System.out.println("Remove "+node);
            return 1;
        }

        int max1 = 0, max2 = 0;
        int children;
        for (Integer child : list) {
            children = getChildren(child, node);
            if (max1 <= max2 && children > max1) {
                max1 = children;
            } else if (max1 > max2 && children > max2) {
                max2 = children;
            }
        }
        return max1 + max2 + 1;
    }
}