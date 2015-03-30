package GCJ2014.round1B.problemA;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws Exception {
        new Main();
    }

    public Main() throws Exception {
        solve();
    }

    private void solve() throws Exception{
        System.out.println(">>> Input: ");
        Scanner sc = new Scanner(System.in);
        BufferedWriter out = new BufferedWriter(new FileWriter("output.txt"));

        int test_cases = sc.nextInt();
        int n;
        String[] array;
        for (int test_case = 1; test_case <= test_cases; test_case++) {
            n = sc.nextInt();
            array = new String[n];
            for (int i = 0; i < n; i++) {
                array[i] = sc.next();
            }
            System.out.println("Solving " + test_case);
            out.write("Case #" + test_case + ": " + getResult(array) + "\n");
        }

        out.flush();
        out.close();
        sc.close();
    }

    private String getResult(String[] array) {
        String[] array2 = array.clone();
        List<Character> chars = new ArrayList<Character>();
        Character current;
        Boolean done = false;
        Boolean solvable = true;
        while (!done) {
            done = true;
            if (array[0].isEmpty()) {
                solvable = false;
                break;
            }
            current = array[0].charAt(0);
            chars.add(current);
            for (int i = 0; i < array.length; i++) {
                int j = 0;
                while (j < array[i].length() && array[i].charAt(j) == current) {
                    j++;
                }
                if (j == 0) {
                    solvable = false;
                }
                if (j < array[i].length()) {
                    done = false;
                    array[i] = array[i].substring(j);
                }
                else {
                    array[i] = "";
                }
            }
        }
        if (!solvable) {
            return "Fegla won";
        }
        array = array2;
        int cost = 0;
        for (Character c : chars) {
            cost += getMinimalCost(array, c);
        }
        return "" + cost;
    }

    private int getMinimalCost(String[] array, Character c) {
        int[] occurences = new int[array.length];
        int nr;
        int j;
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < array.length; i++) {
            nr = 0;
            j = 0;
            while (j < array[i].length() && array[i].charAt(j) != c) {
                j++;
            }
            while (j < array[i].length() && array[i].charAt(j) == c) {
                j++;
                nr++;
            }
            occurences[i] = nr;
            if (nr < min) {
                min = nr;
            }
            if (nr > max) {
                max = nr;
            }
        }

        int cost = 0;
        int min_cost = Integer.MAX_VALUE;
        for (int i = min; i < max; i++) {
            cost = 0;
            for (int occ : occurences) {
                cost += Math.abs(occ - i);
            }
            if (cost < min_cost) {
                min_cost = cost;
            }
        }

        return cost;
    }
}