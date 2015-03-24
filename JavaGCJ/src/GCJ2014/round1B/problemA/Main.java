import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {
        new Main();
    }

    public Main() throws IOException {
        solve();
    }

    private String solve() {
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
        return null;
    }


}