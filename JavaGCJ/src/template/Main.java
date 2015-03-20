import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {
        new Main();
    }

    public Main() throws IOException {
        System.out.println(">>> Input: ");
        Scanner sc = new Scanner(System.in);
        BufferedWriter out = new BufferedWriter(new FileWriter("output.txt"));
        int test_cases = sc.nextInt();
        for (int test_case = 1; test_case <= test_cases; test_case++) {
            System.out.println("Solving " + test_case);
            out.write("Case #" + test_case + ": " + getResult() + "\n");
        }
        out.flush();
        out.close();
        sc.close();
    }

    private String getResult() {
        return null;
    }
}