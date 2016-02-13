package GCJ2015.round1A.problemC;

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

        for (int test_case = 1; test_case <= test_cases; test_case++) {
            

            String result = "Case #" + test_case + ": " + getResult();
            out.write(result + "\n");
            System.out.println(result);
        }

        out.flush();
        out.close();
        sc.close();
    }

    private String getResult() {
        return null;
    }
}