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
    int[] children;
    BitSet visited;

    public Main() throws IOException {
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
        int max = 0;
        for (int i = 0; i < n; i++) {
//            System.out.println("==========\nRoot " + i);
            children = new int[n];
            visited = new BitSet(n);
            setChildren(i);
            if (children[i] > max) {
                max = children[i];
            }
        }
        return "" + (n - max - 1);
    }

    private void setChildren(int node) {
        visited.set(node);

        if (edges[node].cardinality() < 2) {
            children[node] = 0;
//            System.out.println("Remove "+node);
            return ;
        }
        
        ArrayList<Integer> list = new ArrayList<Integer>();
        for (int j=0; j < n; j++) {
            if (edges[node].get(j) && !visited.get(j)) {
                setChildren(j);
                list.add(j);
            }
        }

        int max1 = 0, max2 = 0;
        for (Integer child : list) {
            if (children[child] > max1) {
                max1 = children[child];
            } else if (children[child] > max2) {
                max2 = children[child];
            }
        }
        children[node] = max1 + max2 + 2;
    }
}