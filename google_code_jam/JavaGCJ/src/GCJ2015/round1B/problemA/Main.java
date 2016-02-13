package GCJ2015.round1B.problemA;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws Exception {
        new Main();
    }

    public Main() throws Exception {
        solve();
    }

    private void solve() throws Exception {
        memo = new HashMap<>();
        memo.put(1L, 1);

        // max = 10^14
        // fill memo for large input
//        long max = 100000000000000L;
//        long num;
//        for (int i = 10; i < max; i*=10) {
//            for (int j = 1; j < 10; j++) {
//                num = i*j;
//                System.out.println(num);
//                dp(num);
//            }
//        }

        System.out.println(">>> Input: ");
        Scanner sc = new Scanner(System.in);
        BufferedWriter out = new BufferedWriter(new FileWriter("output.txt"));

        int test_cases = sc.nextInt();
        long n;

        for (int test_case = 1; test_case <= test_cases; test_case++) {
            n = sc.nextLong();

            String result = "Case #" + test_case + ": " + getResult(n);
            out.write(result + "\n");
            System.out.println(result);
        }

        out.flush();
        out.close();
        sc.close();
    }

    Map<Long, Integer> memo;

    private String getResult(long number) {

        return dp(number) + "";

    }

    private int dp(long number) {
        if (memo.containsKey(number)) {
            return memo.get(number);
        }
        // -1
        int c_min = dp(number - 1) + 1;

        long reverse = reverse(number);

        // switch
        int c_switch;
        if (reverse >= number || number != reverse(reverse)) {
            c_switch = Integer.MAX_VALUE;
        }
        else {
            c_switch = dp(reverse) + 1;
        }

        if (c_min < c_switch) {
            memo.put(number, c_min);
            return c_min;
        } else {
//            System.out.println("switch " + number);
            memo.put(number, c_switch);
            return c_switch;
        }
    }

    private long reverse(long n) {
        long reverse = 0;
        while( n != 0 ) {
            reverse = reverse * 10;
            reverse = reverse + n%10;
            n = n/10;
        }
        return reverse;
    }

}