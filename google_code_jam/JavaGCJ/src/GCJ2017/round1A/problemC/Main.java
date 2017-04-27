package round1A.problemC;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {

    Map<Integer[], Integer> memo = new HashMap<>();

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

            int Hd = sc.nextInt();
            int Ad = sc.nextInt();
            int Hk = sc.nextInt();
            int Ak = sc.nextInt();
            int B = sc.nextInt();
            int D = sc.nextInt();

            Integer[] state = { Hd, Hd, Ad, Hk, Ak, B, D };
            int res = getResult(state);
            String resStr = "";
            if (res == Integer.MAX_VALUE) {
                resStr += "IMPOSSIBLE";
            } else {
                resStr += res;
            }

            String result = "Case #" + test_case + ": " + resStr;
            out.write(result + "\n");
            System.out.println(result);
        }

        out.flush();
        out.close();
        sc.close();
    }

    // state: oHd, cHd, Ad, cHk, Ak, B, D
    private int getResult(Integer[] state) {
        if (memo.containsKey(state)) {
            return memo.get(state);
        }

        // check solution
        if (state[3] <= 0) {
            memo.put(state, 0);
            return 0;
        }
        if (state[1] <= 0) {
            memo.put(state, Integer.MAX_VALUE);
            return Integer.MAX_VALUE;
        }

        Integer[] newState = new Integer[state.length];
        int min = Integer.MAX_VALUE;

        // attack
        System.arraycopy(state, 0, newState, 0, state.length);
        newState[3] -= state[2];
        int attack = getResult(newState);
        if (attack < min) {
            min = attack;
        }

        // buff
        System.arraycopy(state, 0, newState, 0, state.length);
        newState[2] += state[5];
        int buff = getResult(newState);
        if (buff < min) {
            min = buff;
        }

        // cure
        System.arraycopy(state, 0, newState, 0, state.length);
        newState[1] = state[0];
        int cure = getResult(newState);
        if (cure < min) {
            min = cure;
        }

        // debuff
        System.arraycopy(state, 0, newState, 0, state.length);
        newState[4] -= state[6];
        int debuff = getResult(newState);
        if (debuff < min) {
            min = debuff;
        }

        return min+1;
    }

}