package GCJ2015.qualificationRound.problemB;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
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

        int diners;
        for (int test_case = 1; test_case <= test_cases; test_case++) {
            System.out.println("Solving " + test_case);

            diners = sc.nextInt();
            List<Integer> pancakes = new ArrayList<>(diners);
            for (int i = 0; i < diners; i++) {
                pancakes.add(sc.nextInt());
            }

            out.write("Case #" + test_case + ": " + getResult(pancakes, 0) + "\n");
        }

        out.flush();
        out.close();
        sc.close();
    }

    private int getResult(List<Integer> pancakes, int turn) {
        if (pancakes.isEmpty()) {
            return turn;
        }

        List<Integer> special = new ArrayList<>();
        List<Integer> eat = new ArrayList<>();
        int max = Collections.max(pancakes);
        boolean specialDone = false;

        for (int i : pancakes) {
            if (i == max && !specialDone) {
                special.add((int) Math.floor((double) i / 2));
                special.add((int) Math.ceil((double) i / 2));
                specialDone = true;
            }
            else {
                special.add(i);
            }
            if (i > 1) {
                eat.add(i - 1);
            }
        }

        int costEat = getResult(eat, turn+1);
        // Limit
        if (max <= 3) {
            return costEat;
        }
        int costSpecial = getResult(special, turn+1);

        return Math.min(costEat, costSpecial);
    }
}