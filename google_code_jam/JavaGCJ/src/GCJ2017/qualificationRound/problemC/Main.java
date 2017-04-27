package problemC;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.BitSet;
import java.util.Scanner;

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
            long N = sc.nextLong();
            long K = sc.nextLong();

            String result = "Case #" + test_case + ": " + getResult(N, K);
            // String result = "Case #" + test_case + ": " + getResult2((int) N, (int) K);
            out.write(result + "\n");
            System.out.println(result);
        }

        out.flush();
        out.close();
        sc.close();
    }

    private String getResult(long N, long K) {
        long P = (long) Math.pow(2, Math.floor(Math.log(K)/Math.log(2)));
        int max = (int) Math.ceil(((double) (N-K-P+1))/(2*P));
        int min = (int) Math.ceil(((double) (N-K-2*P+1))/(2*P));

        return max + " " + min;
    }

    private String getResult2(int N, int K) {
        int length = N + 2;
        BitSet stalls = new BitSet(length);
        stalls.set(0);
        stalls.set(length-1);

        int bestLS=0, bestRS=0;
        int bestMin, bestMax, bestS;
        for (int i = 0; i < K; i++) {
            bestLS = -1;
            bestRS = -1;
            bestMin = -1;
            bestMax = -1;
            bestS = -1;

            for (int j = 0; j < length; j++) {
                int RS = Math.max(0, stalls.nextSetBit(j) - j - 1);
                int LS = Math.max(0, j - stalls.previousSetBit(j) - 1);
                int min = Math.min(LS, RS);
                int max = Math.max(LS, RS);
                if (min > bestMin || (min == bestMin && max > bestMax)) {
                    bestLS = LS;
                    bestRS = RS;
                    bestMin = min;
                    bestMax = max;
                    bestS = j;
                }
            }

            stalls.set(bestS);
        }

        return Math.max(bestLS, bestRS) + " " + Math.min(bestLS, bestRS);
    }
}