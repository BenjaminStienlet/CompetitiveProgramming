import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {
        new Main();
    }

    public Main() throws IOException {
        Scanner sc = new Scanner(System.in);
        BufferedWriter out = new BufferedWriter(new FileWriter("output.txt"));
        int test_cases = sc.nextInt();
        for (int test_case = 1; test_case <= test_cases; test_case++) {
            out.write("Case #" + test_case + ": " + getResult() + "\n");
        }
        out.flush();
        out.close();
        sc.close();
    }

    public String getResult() {
        return null;
    }
}