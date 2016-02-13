package GCJ2015.round1C.problemB;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.Map;
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

        int K, L, S;
        String alphabet, target;
        for (int test_case = 1; test_case <= test_cases; test_case++) {
            K = sc.nextInt();
            L = sc.nextInt();
            S = sc.nextInt();
            alphabet = sc.next();
            target = sc.next();

            String result = "Case #" + test_case + ": " + getResult(K, L, S, alphabet, target);
            out.write(result + "\n");
            System.out.println(result);
        }

        out.flush();
        out.close();
        sc.close();
    }

    private String getResult(int K, int L, int S, String alphabet, String target) {
        if (!possible(alphabet, target)) {
            return "0.0";
        }
        int bananas;
        int nr_overlap = getOverlap(target);
        if (nr_overlap == 0) {
            bananas =  S/L;
        }
        else if (nr_overlap == L) {
            bananas = S-1;
        }
        else {
            bananas = 1 + (S-L)/(L-nr_overlap);
        }
        double prob = 1;
        Map<Character, Double> alph = new HashMap<>();
        for (int i = 0; i < alphabet.length(); i++) {
            if (alph.containsKey(alphabet.charAt(i))) {
                alph.put(alphabet.charAt(i), alph.get(alphabet.charAt(i)) + 1);
            }
            else {
                alph.put(alphabet.charAt(i), 1.0);
            }
        }

//        double result = bananas;
        double total = bananas; // Average from for loop
        int count = 1;
        for (int i = 1; i <= bananas; i++) {
//            result -= bananas*Math.pow(prob, i); // Wrong when overlap
            String t = target;
            for (int j = 1; j < i; j++) {
                t += target.substring(nr_overlap);
            }
            for (int k = 0; k < t.length(); k++) {
                prob *= (alph.get(t.charAt(k))/K);
            }
            int times = S / t.length();
            total += times*bananas*prob;
            count += times;
        }
        double result = bananas - (total / count);
        return "" + result;
    }

    private boolean possible(String a, String b) {
        for (int i = 0; i < b.length(); i++){
            if (!a.contains(b.substring(i,i+1)))
                return false;
        }
        return true;
    }

    private int getOverlap(String a) {
        for (int i = 1; i <= a.length(); i++) {
            if (!a.substring(0,i).equals(a.substring(a.length()-i))) {
                return i-1;
            }
        }
        return a.length();
    }
}