package problemB;

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

    private void solve() throws Exception {
        System.out.println(">>> Input: ");
        Scanner sc = new Scanner(System.in);
        BufferedWriter out = new BufferedWriter(new FileWriter("output.txt"));

        int test_cases = sc.nextInt();

        for (int test_case = 1; test_case <= test_cases; test_case++) {
            String N = sc.next();

            String result = "Case #" + test_case + ": " + getResult(N);
            out.write(result + "\n");
            System.out.println(result);
        }

        out.flush();
        out.close();
        sc.close();
    }

    private String getResult(String N) {
        List<Integer> number = new ArrayList<>();
        int length = N.length();
        for (int i = 0; i < length; i++) {
            number.add(Integer.parseInt(N.substring(i,i+1)));
        }

        while (true) {
            int notTidyIndex = 1;
            while (notTidyIndex < length) {
                if (number.get(notTidyIndex) < number.get(notTidyIndex-1)) {
                    break;
                }
                notTidyIndex++;
            }
            if (notTidyIndex >= length) {
                break;
            }
            number.set(notTidyIndex-1, number.get(notTidyIndex-1)-1);
            for (int i = notTidyIndex; i < length; i++) {
                number.set(i, 9);
            }
        }

        StringBuilder sb = new StringBuilder();
        boolean removeZeros = true;
        for (int i = 0; i < length; i++) {
            if (!removeZeros || number.get(i) != 0) {
                removeZeros = false;
                sb.append(number.get(i));
            }
        }

        return sb.toString();
    }
}