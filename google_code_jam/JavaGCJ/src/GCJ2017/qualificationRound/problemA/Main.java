package problemA;

import java.io.BufferedWriter;
import java.io.FileWriter;
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
            String S = sc.next();
            int K = sc.nextInt();

            String result = "Case #" + test_case + ": " + getResult(S, K);
            out.write(result + "\n");
            System.out.println(result);
        }

        out.flush();
        out.close();
        sc.close();
    }

    private String getResult(String S, int K) {
        int length = S.length();
        BitSet start = new BitSet(length);
        BitSet target = new BitSet(length);
        target.set(0, length);
        for (int i = 0; i < length; i++) {
            start.set(i, S.charAt(i) == '+');
        }

        Queue<Pair<Integer, BitSet>> queue = new LinkedList<>();
        queue.add(new Pair<>(0, start));
        Set<BitSet> seen = new HashSet<>();
        seen.add(start);

        while (!queue.isEmpty()) {
            Pair<Integer, BitSet> current = queue.poll();
            if (current.v2.equals(target)) {
                return current.v1.toString();
            }
            for (int i = 0; i <= length-K; i++) {
                BitSet cloned = (BitSet) current.v2.clone();
                cloned.flip(i, i + K);
                if (!seen.contains(cloned)) {
                    queue.add(new Pair<>(current.v1 + 1, cloned));
                    seen.add(cloned);
                }
            }
        }

        return "IMPOSSIBLE";
    }

    class Pair<V1,V2> {

        public final V1 v1;
        public final V2 v2;

        public Pair(V1 v1, V2 v2) {
            this.v1 = v1;
            this.v2 = v2;
        }

        @Override
        public boolean equals(Object o) {
            if (!(o instanceof Pair)) {
                return false;
            }
            Pair<?, ?> p = (Pair<?, ?>) o;
            return Objects.equals(p.v1, v1) && Objects.equals(p.v2, v2);
        }

        @Override
        public int hashCode() {
            return (v1 == null ? 0 : v1.hashCode()) ^ (v2 == null ? 0 : v2.hashCode());
        }

    }

}