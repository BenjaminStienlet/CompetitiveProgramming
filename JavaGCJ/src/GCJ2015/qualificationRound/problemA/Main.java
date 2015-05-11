package GCJ2015.qualificationRound.problemA;

import java.io.BufferedWriter;
import java.io.FileWriter;
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

        int max_level;
        int[] publicLevels;
        for (int test_case = 1; test_case <= test_cases; test_case++) {
            System.out.println("Solving " + test_case);

            max_level = sc.nextInt();
            publicLevels = new int[max_level+1];

            String line = sc.next();
            for(int i = 0;i < line.length(); i++) {
                publicLevels[i] = Integer.parseInt(String.valueOf(line.charAt(i)));
            }

            out.write("Case #" + test_case + ": " + getResult(publicLevels) + "\n");
        }

        out.flush();
        out.close();
        sc.close();
    }

    private String getResult(int[] publicLevels) {
        int standing = 0;
        int friends = 0;
        for (int i = 0; i < publicLevels.length; i++) {
            if (i > standing) {
                int diff = i - standing;
                friends += diff;
                standing += diff;
            }
            standing += publicLevels[i];
        }
        return "" + friends;
    }
}