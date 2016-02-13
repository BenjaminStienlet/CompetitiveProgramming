package GCJ2015.qualificationRound.problemC;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.BitSet;
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
            System.out.println("Solving " + test_case);

            int L = sc.nextInt();
            int X = sc.nextInt();
            String s = sc.next();
            String input = "";
            for (int i = 0; i < X; i++) {
                input += s;
            }

            out.write("Case #" + test_case + ": " + getResult(input) + "\n");
        }

        out.flush();
        out.close();
        sc.close();
    }

    private String getResult(String input) {
        String result = "1";
        BitSet indicesI = new BitSet(input.length());
        for (int i = 0; i < input.length()-2; i++) {
            result = multiply(result, String.valueOf(input.charAt(i)));
            if (result.equals("i")) {
                indicesI.set(i);
            }
        }
        result = "1";
        BitSet indicesK = new BitSet(input.length());
        for (int k = input.length()-1; k > 1; k--) {
            result = multiply(String.valueOf(input.charAt(k)), result);
            if (result.equals("k")) {
                indicesK.set(k);
            }
        }

//        int i = 0;
//        while (indicesI.nextSetBit(i) != -1) {
//            i = indicesI.nextSetBit(i);
//            int k = i+2;
//            while (indicesK.nextSetBit(k) != -1) {
//                k = indicesK.nextSetBit(k);
//                result = multiply(input.substring(i + 1, k));
//                if (result.equals("j")) {
//                    return "YES";
//                }
//                k++;
//            }
//            i++;
//        }
//
//        return "NO";

        int i = 0;
        while (indicesI.nextSetBit(i) != -1) {
            i = indicesI.nextSetBit(i);
            int k = i+2;
            result = "1";
            int newK;
            while (indicesK.nextSetBit(k) != -1) {
                newK = indicesK.nextSetBit(k);
                result = multiply(result, multiply(input.substring(k-1, newK)));
                if (result.equals("j")) {
                    return "YES";
                }
                k = newK + 1;
            }
            i++;
        }

        return "NO";
    }

//    private String getResult(String input) {
//        String result = "1";
//        for (int i = 0; i < input.length()-2; i++) {
//            result = multiply(result, String.valueOf(input.charAt(i)));
//            if (result.equals("i")) {
//                result = "1";
//                for (int j = i+1; j < input.length() - 1; j++) {
//                    result = multiply(result, String.valueOf(input.charAt(j)));
//                    if (result.equals("j")) {
//                        result = multiply(input.substring(j+1));
//                        if (result.equals("k")) {
//                            return "YES";
//                        }
//                    }
//                }
//            }
//        }
//        return "NO";
//    }

    private String multiply(String s) {
        if (s.length() <= 1) {
            return s;
        }
        String a = String.valueOf(s.charAt(0));
        for (int i = 1; i < s.length(); i++) {
            a = multiply(a, String.valueOf(s.charAt(i)));
        }
        return a;
    }
    
    private String multiply(String a, String b) {
        Boolean min = false;
        if (a.charAt(0) == '-') {
            min = true;
            a = a.substring(1);
        }
        if (b.charAt(0) == '-') {
            min = !min;
            b = b.substring(1);
        }
        String result = "";
        if (a.equals("1")) {
            if (b.equals("1")) result = "1";
            else if (b.equals("i")) result = "i";
            else if (b.equals("j")) result = "j";
            else if (b.equals("k")) result = "k";
        }
        else if (a.equals("i")) {
            if (b.equals("1")) result = "i";
            else if (b.equals("i")) {
                result = "1";
                min = !min;
            }
            else if (b.equals("j")) result = "k";
            else if (b.equals("k")) {
                result = "j";
                min = !min;
            }
        }
        else if (a.equals("j")) {
            if (b.equals("1")) result = "j";
            else if (b.equals("i")) {
                result = "k";
                min = !min;
            }
            else if (b.equals("j")) {
                result = "1";
                min = !min;
            }
            else if (b.equals("k")) result = "i";
        }
        else if (a.equals("k")) {
            if (b.equals("1")) result = "k";
            else if (b.equals("i")) result = "j";
            else if (b.equals("j")) {
                result = "i";
                min = !min;
            }
            else if (b.equals("k")) {
                result = "1";
                min = !min;
            }
        }
        if (min) {
            result = "-" + result;
        }
        return result;
    }
}

