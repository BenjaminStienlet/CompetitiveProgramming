package GCJ2015.round1A.problemB;

import org.javatuples.Pair;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.math.BigInteger;
import java.util.*;

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
            int B = sc.nextInt();
            int N = sc.nextInt();

            int[] barbers = new int[B];
            for (int i = 0; i < B; i++) {
                barbers[i] = sc.nextInt();
            }

            String result = "Case #" + test_case + ": " + getResult(barbers, N);
            out.write(result + "\n");
            System.out.println(result);
        }

        out.flush();
        out.close();
        sc.close();
    }

    private String getResult(int[] bDur, int N) {
        int B = bDur.length;
        int Bmin = Integer.MAX_VALUE;
        int Bmax = Integer.MIN_VALUE;
        for (int i = 0; i < B; i++) {
            if (bDur[i] < Bmin) Bmin = bDur[i];
            if (bDur[i] > Bmax) Bmax = bDur[i];
        }

        BigInteger minT = (BigInteger.valueOf(N-1).multiply(BigInteger.valueOf(Bmin)).divide(BigInteger.valueOf(B)));
        BigInteger maxT = (BigInteger.valueOf(N-1).multiply(BigInteger.valueOf(Bmax)).divide(BigInteger.valueOf(B))).add(BigInteger.ONE);
        BigInteger midT;
        BigInteger two = BigInteger.valueOf(2);
        BigInteger c;
        BigInteger bigN = BigInteger.valueOf(N);

        while (!minT.equals(maxT)) {
            midT = minT.add(maxT).divide(two);
            BigInteger count = BigInteger.ZERO;
            for (int i = 0; i < B; i++) {
                c = midT.divide(BigInteger.valueOf(bDur[i]));
                if (midT.multiply(BigInteger.valueOf(bDur[i])).compareTo(c) < 0)
                    c = c.add(BigInteger.ONE);
                count = count.add(c);
            }
            if (count.compareTo(bigN) >= 0) {
                if (maxT.equals(midT)) break;
                maxT = midT;
            }
            else {
                if (minT.equals(midT)) break;
                minT = midT;
            }
        }
        BigInteger T = minT;

        Queue<BigInteger> q = new PriorityQueue<>();

        Map<BigInteger, BitSet> bFree = new HashMap<>();
        BigInteger client = BigInteger.ZERO;
        BigInteger newTime;

        for (int i = 0; i < B; i++) {
            c = T.divide(BigInteger.valueOf(bDur[i]));
            if (T.multiply(BigInteger.valueOf(bDur[i])).compareTo(c) < 0)
                c = c.add(BigInteger.ONE);
            client = client.add(c);
            newTime = c.multiply(BigInteger.valueOf(bDur[i]));
            if (!bFree.containsKey(newTime)) {
                bFree.put(newTime, new BitSet(B));
            }
            bFree.get(newTime).set(i);
            if (! q.contains(newTime)) {
                q.add(newTime);
            }
        }

        BigInteger time;
        BitSet curr;
        while (! q.isEmpty()) {
            time = q.poll();
            curr = bFree.get(time);
            int i = 0;
            while(curr.nextSetBit(i) != -1) {
                i = curr.nextSetBit(i);

                if (bigN.subtract(client).equals(BigInteger.ONE)) {
                    return "" + (i+1);
                }
                newTime = time.add(BigInteger.valueOf(bDur[i]));
                if (!bFree.containsKey(newTime)) {
                    bFree.put(newTime, new BitSet(B));
                }
                bFree.get(newTime).set(i);
                if (! q.contains(newTime)) {
                    q.add(newTime);
                }

                client = client.add(BigInteger.ONE);
                i++;
            }
        }

        return null;

//        ArrayList<Pair<Integer, Integer>> list = new ArrayList<>();
//        int count = 0;
//        int curr;
//        for (int i = 0; i < B; i++) {
//            curr = (int) Math.floor(T / bDur[i]);
//            count += curr;
//            list.add(new Pair<>(curr, i));
//        }
//
//        Collections.sort(list);
//
//        return "" + (list.get(N-count-1).getValue1()+1);


    }

//    SIMULATION -> TOO SLOW
//    private String getResult(int[] bDur, int number) {
//        int B = bDur.length;
//        Queue<Integer> q = new PriorityQueue<Integer>();
//        q.add(0);
//
//        Map<Integer, BitSet> bFree = new HashMap<>();
//        BitSet init = new BitSet(B);
//        init.set(0,B);
//        bFree.put(0,init);
//
//        int time, newTime;
//        BitSet curr;
//        int client = 0;
//        while (! q.isEmpty()) {
//            time = q.poll();
//            curr = bFree.get(time);
//            int i = 0;
//            while(curr.nextSetBit(i) != -1) {
//                i = curr.nextSetBit(i);
//
//                if (client == number-1) {
//                    return "" + (i+1);
//                }
//                newTime = time + bDur[i];
//                if (!bFree.containsKey(newTime)) {
//                    bFree.put(newTime, new BitSet(B));
//                }
//                bFree.get(newTime).set(i);
//                if (! q.contains(newTime)) {
//                    q.add(newTime);
//                }
//
//                client++;
//                i++;
//            }
//        }
//
//        return null;
//    }
}