package GCJ2015.round1B.problemC;

import org.javatuples.Pair;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        new Main();
    }

    public Main() throws Exception {
        solve();
    }

    private void solve() throws Exception {
        System.out.println(">>> Input: ");
        Scanner sc = new Scanner(System.in);
        BufferedWriter out = new BufferedWriter(new FileWriter("output.txt"));

        int test_cases = sc.nextInt();
        int N;
        int[][] hikers; // starting position (D), number of hikers (H),
                        // amount of time for the fastest hiker go around the circle (M)
                        // 1st hiker: M, 2nd: M+1, 3rd: M+2, ...
        for (int test_case = 1; test_case <= test_cases; test_case++) {
            N = sc.nextInt();
            hikers = new int[N][3];
            for (int i = 0; i < N; i++) {
                hikers[i][0] = sc.nextInt();
                hikers[i][1] = sc.nextInt();
                hikers[i][2] = sc.nextInt();
            }

            String result = "Case #" + test_case + ": " + getResult(hikers);
            out.write(result + "\n");
            System.out.println(result);
        }

        out.flush();
        out.close();
        sc.close();
    }

    private String getResult(int[][] hikers) {
        List<Integer> list = new ArrayList<>();

        for (int i = 0; i < hikers.length; i++) {
            for (int j = 0; j < hikers[i][1]; j++) {
                list.add(hikers[i][2]+j);
            }
        }

        Queue<Pair<Integer, Integer>> queue = new PriorityQueue<>();
        queue.add(new Pair<>(0,0));

        while (!queue.isEmpty()) {
            Pair<Integer, Integer> pair = queue.poll();

            for (int speed : list) {
                // try each possible speed, calculate encounters and add them to the event queue
                // idea
            }
        }

        return null;
    }
}