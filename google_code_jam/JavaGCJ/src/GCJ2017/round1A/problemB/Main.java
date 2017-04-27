package round1A.problemB;

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

        for (int test_case = 1; test_case <= test_cases; test_case++) {
            int N = sc.nextInt();
            int P = sc.nextInt();

            int[] R = new int[N];
            for (int i = 0; i < N; i++) {
                R[i] = sc.nextInt();
            }

            int[][] Q = new int[N][P];
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < P; j++) {
                    Q[i][j] = sc.nextInt();
                }
            }

            String result = "Case #" + test_case + ": " + getResult(N, P, R, Q);
            out.write(result + "\n");
            System.out.println(result);
        }

        out.flush();
        out.close();
        sc.close();
    }

    private String getResult(int N, int P, int[] R, int[][] Q) {

        List<Queue<Integer>> queues = new ArrayList<>();
        for (int i = 0; i < N; i ++) {
            Queue<Integer> queue = new PriorityQueue<>();
            for (int j = 0; j < P; j++) {
                queue.add(Q[i][j]);
            }
            queues.add(queue);
        }

        int nrKits = 0;
        while (true) {
            int[] ingredients = new int[N];
            for (int i = 0; i < N; i++) {
                if (queues.get(i).isEmpty()) {
                    return "" + nrKits; // RETURN
                } else {
                    ingredients[i] = queues.get(i).poll();
                }
            }

            boolean isValidKit = false;
            while (!isValidKit) {

                int[] maxPossibleKits = new int[N];
                int[] minPossibleKits = new int[N];
                for (int i = 0; i < N; i++) {
                    int max = (int) Math.ceil(ingredients[i] / R[i]);
                    int min = (int) Math.floor(ingredients[i] / R[i]);
                    if (((double) ingredients[i])/(max*R[i]) <= 1.1) {
                        maxPossibleKits[i] = max;
                        minPossibleKits[i] = max;
                    } else {
                        maxPossibleKits[i] = -1;
                    }
                    if (((double) ingredients[i])/(min*R[i]) >= 0.9) {
                        minPossibleKits[i] = min;
                        if (maxPossibleKits[i] == -1) {
                            maxPossibleKits[i] = min;
                        }
                    } else {
                        minPossibleKits[i] = -1;
                    }
                }


            }
        }
    }
}