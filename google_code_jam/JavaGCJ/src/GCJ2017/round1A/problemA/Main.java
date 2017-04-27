package round1A.problemA;

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
            int R = sc.nextInt();
            int C = sc.nextInt();
            sc.nextLine();

            char[][] cake = new char[R][C];
            for (int i = 0; i < R; i++) {
                String inputLine = sc.nextLine();
                for (int j = 0; j < C; j++) {
                    cake[i][j] = inputLine.charAt(j);
                }
            }

            String result = "Case #" + test_case + ": " + getResult(R, C, cake);
            out.write(result + "\n");
            System.out.println(result);
        }

        out.flush();
        out.close();
        sc.close();
    }

    private String getResult(int R, int C, char[][] cake) {

        char[][] newCake = new char[R][C];
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                newCake[i][j] = cake[i][j];
            }
        }

        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (cake[i][j] == '?') {
                    continue;
                }
                int jR, jL, iU, iD;
                for (jR = j+1; jR < C; jR++) {
                    if (newCake[i][jR] != '?') {
                        break;
                    }
                }
                jR--;
                for (jL = j-1; jL >= 0; jL--) {
                    if (newCake[i][jL] != '?') {
                        break;
                    }
                }
                jL++;
                for (iU = i+1; iU < R; iU++) {
                    boolean correct = true;
                    for (int k = jL; k <= jR; k++) {
                        if (newCake[iU][k] != '?') {
                            correct = false;
                        }
                    }
                    if (!correct) {
                        break;
                    }
                }
                iU--;
                for (iD = i-1; iD >= 0; iD--) {
                    boolean correct = true;
                    for (int k = jL; k <= jR; k++) {
                        if (newCake[iD][k] != '?') {
                            correct = false;
                        }
                    }
                    if (!correct) {
                        break;
                    }
                }
                iD++;

                for (int k = iD; k <= iU; k++) {
                    for (int l = jL; l <= jR; l++) {
                        newCake[k][l] = cake[i][j];
                    }
                }
            }
        }

        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < R; i++) {
            builder.append("\n");
            for (int j = 0; j < C; j++) {
                builder.append(newCake[i][j]);
            }
        }

        return builder.toString();
    }
}