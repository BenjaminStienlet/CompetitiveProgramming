package final_2014;

import org.javatuples.Pair;
import org.javatuples.Triplet;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.*;


// =====================================================================================================================
// RESULTS
// =====================================================================================================================


// ========================
// SCORES - 30/3 23u
// maxDepth : score, time
// 3 : 201369, 16s
// 4 : 167155, 45s
// 5 : 116197, 130s
// 6 : 434405, 389s
// 7 : 75115, 1152s
// ========================

// ========================
// SCORES - 31/3 11u
// maxDepth : score, time
// 3 : 309534, 19s
// 4 : 328284, 49s
// 5 : 217013, 145s
// 6 : 434405, 523s
// 7 : 75115, 1152s
// ========================

// ========================
// SCORES - 31/3 13u
// maxDepth : score, time
// 3 : 249024, 16s
// 4 : 282453, 45s
// 5 : 360422, 154s
// 6 : 448390, 389s
// 7 : 418663, 1560s
// ========================

// ========================
// SCORES - 31/3 21u
// maxDepth : score, time
// 3 : 124486, 6s
// 4 : 147227, 21s
// 5 : 262291, 52s
// 6 : 215373, 171s
// 7 : 351180, 547s
// 8 : 334605, 1685s
// 9 : 285563, 9460s
// 10: 231268, 25860s
// ========================

// ========================
// SCORES - 01/4 11u
// maxDepth : score, time
// 3 : 180730
// 4 : 187211
// 5 :
// 6 :
// 7 :
// ========================


// =====================================================================================================================
// MAIN
// =====================================================================================================================


public class Main {

    int maxDepth = 4;

    public static void main(String[] args) throws Exception {
        new Main();
    }

    public Main() throws Exception {
        solve();
    }

    private void solve() throws Exception {

        // BEGIN input
        System.out.println(">>> Input: ");
        Scanner sc = new Scanner(System.in);

        nrRows = sc.nextInt();          // R
        nrColumns = sc.nextInt();       // C
        nrAltitudes = sc.nextInt();     // A
        nrTargets = sc.nextInt();       // L
        nrRadius = sc.nextInt();        // V
        nrBalloons = sc.nextInt();      // B
        nrTurns = sc.nextInt();         // T

        Triplet<Integer, Integer, Integer> startCell = new Triplet<Integer, Integer, Integer>(0, sc.nextInt(), sc.nextInt());

        // Read targets
        targets = new ArrayList<Pair<Integer, Integer>>(nrTargets);
        for (int i = 0; i < nrTargets; i++) {
            targets.add(i, new Pair<Integer, Integer>(sc.nextInt(), sc.nextInt()));
        }

        // Read windvectors
        windVectors = new HashMap<Triplet<Integer, Integer, Integer>, Pair<Integer, Integer>>();
        for (int i = 1; i <= nrAltitudes; i++) {
            for (int j = 0; j < nrRows; j++) {
                for (int k = 0; k < nrColumns; k++) {
                    windVectors.put(new Triplet<Integer, Integer, Integer>(i, j, k),
                            new Pair<Integer, Integer>(sc.nextInt(), sc.nextInt()));
                }
            }
        }
        // END input

        int maxScore = 0, score;
        memoPosition = new HashMap<Triplet<Integer, Integer, Integer>, Triplet<Integer, Integer, Integer>>();

        while (true) {
            solution = new int[nrTurns][nrBalloons];

            // Add startCell to balloon positions at turn -1
            balloonPosition = new HashMap<Pair<Integer, Integer>, Triplet<Integer, Integer, Integer>>();
            for (int i = 0; i < nrBalloons; i++) {
                balloonPosition.put(new Pair<Integer, Integer>(i, -1), startCell);
            }

            // Create a bitset with nrTargets zeros for every turn
            covered = new BitSet[nrTurns];
            for (int i = 0; i < nrTurns; i++) {
                covered[i] = new BitSet(nrTargets);
            }

            long startTime = System.currentTimeMillis();

            getResult();

            long endTime = System.currentTimeMillis();
            System.out.println("That took " + (endTime - startTime) + " milliseconds");

            score = score();
            System.out.println("Score: " + score + ", maxScore: " + maxScore + "\n");

            if (score > maxScore) {
                maxScore = score;
                System.out.println("\n=======================\nNew max score: " + score
                        + "\n=======================\n");
                // Create string to write solution to file
                StringBuilder builder = new StringBuilder();
                for (int i = 0; i < nrTurns; i++) {
                    for (int j = 0; j < nrBalloons; j++) {
                        builder.append(solution[i][j]);
                        builder.append(" ");
                    }
                    builder.deleteCharAt(builder.length() - 1);
                    builder.append("\n");
                }

                BufferedWriter out = new BufferedWriter(new FileWriter("output_" + maxDepth + ".txt"));
                out.append(builder);

                out.flush();
                out.close();
                sc.close();
            }

        }
    }


    // =================================================================================================================
    // VARIABLES
    // =================================================================================================================

    int nrRows;          // R
    int nrColumns;       // C
    int nrAltitudes;     // A
    int nrTargets;       // L
    int nrRadius;        // V
    int nrBalloons;      // B
    int nrTurns;         // T

    List<Pair<Integer, Integer>> targets;    // [nrTargets][2]
    int[][] solution;   // [Turn][Balloon] -> -1, 0, +1
    Map<Triplet<Integer, Integer, Integer>, Pair<Integer, Integer>> windVectors;    // [Altitude][Row][Column][2] -> a, b
    Map<Pair<Integer, Integer>, Triplet<Integer, Integer, Integer>> balloonPosition; // [Balloon, Turn] : [Altitude, Row, Column]
    BitSet[] covered;   // [nrTurns], length nrTargets

    Map<Triplet<Integer, Integer, Integer>, Triplet<Integer, Integer, Integer>> memoPosition;

    Triplet<Integer, Integer, Integer> outsideField = new Triplet<Integer, Integer, Integer>(-1, -1, -1);


    // =================================================================================================================
    // SOLUTION
    // =================================================================================================================

    private void getResult() {

        Pair<Integer, List<Integer>> dfs_result;
        Triplet<Integer, Integer, Integer> position;
        Triplet<Integer, Integer, Integer> newPosition;

        for (int balloon = 0; balloon < nrBalloons; balloon++) {
            System.out.println("Balloon " + balloon);

            for (int turn = 0; turn < nrTurns; turn++) {

                position = balloonPosition.get(new Pair<Integer, Integer>(balloon, turn - 1));

                if (position.equals(outsideField)) {
                    balloonPosition.put(new Pair<Integer, Integer>(balloon, turn), outsideField);
                    solution[turn][balloon] = 0;
                    continue;
                }

                dfs_result = dfs(turn, position, 0);

                // TODO: mogelijk om dfs_result van de volgende 5 stappen als solution toe te voegen
                // en 5 stappen verder te gaan
                solution[turn][balloon] = dfs_result.getValue1().get(0);
                newPosition = nextPosition(position.setAt0(position.getValue0() + dfs_result.getValue1().get(0)));

                // Set the balloon position
                if (newPosition == null) {
                    balloonPosition.put(new Pair<Integer, Integer>(balloon, turn), outsideField);
                }
                else {
                    balloonPosition.put(new Pair<Integer, Integer>(balloon, turn), newPosition);
                    covered[turn].or(coveredTargetsByBalloon(turn, newPosition));
                }
            }
        }

    }

    private Pair<Integer, List<Integer>> dfs(int turn, Triplet<Integer, Integer, Integer> position, int depth) {

        int score;
        if (turn >= nrTurns-1) {
             score = 0;
        }
        else {
            score = coveredTargetsByBalloon(turn, position).cardinality();
        }

        if (depth >= maxDepth || turn >= nrTurns) {
            return new Pair<Integer, List<Integer>>(score, new ArrayList<Integer>());
        }

        Pair<Integer, List<Integer>> score_min1, score_0, score_plus1;
        Triplet<Integer, Integer, Integer> newPosition;

        // -1
        newPosition = nextPosition(position.setAt0(position.getValue0() - 1));
        if (newPosition == null) {
            List<Integer> list = new ArrayList<Integer>();
            list.add(-1);
            // TODO: score = - weight*(nrTurns - turn)
            score_min1 = new Pair<Integer, List<Integer>>(Integer.MIN_VALUE, list);
        }
        else {
            score_min1 = dfs(turn + 1, newPosition, depth + 1);
            score_min1.getValue1().add(0, -1);
        }

        // 0
        newPosition = nextPosition(position);
        if (newPosition == null){
            List<Integer> list = new ArrayList<Integer>();
            list.add(0);
            score_0 = new Pair<Integer, List<Integer>>(Integer.MIN_VALUE, list);
        }
        else {
            score_0 = dfs(turn + 1, newPosition, depth + 1);
            score_0.getValue1().add(0, 0);
        }

        // +1
        newPosition = nextPosition(position.setAt0(position.getValue0() + 1));
        if (newPosition == null){
            List<Integer> list = new ArrayList<Integer>();
            list.add(1);
            score_plus1 = new Pair<Integer, List<Integer>>(Integer.MIN_VALUE, list);
        }
        else {
            score_plus1 = dfs(turn + 1, newPosition, depth + 1);
            score_plus1.getValue1().add(0, 1);
        }

        // Choose -1, 0 or +1
        ArrayList<Pair<Integer, List<Integer>>> list = new ArrayList<Pair<Integer, List<Integer>>>(
                Arrays.asList(score_plus1, score_0, score_min1));
        // Collections.sort(list, dfsComparator);
        // Collections.reverse(list);

        Pair<Integer, List<Integer>> result;
        int max = Integer.MIN_VALUE;
        boolean removed;
        // Remove invalid choices and calculate maxScore
        for (int i = 0; i < list.size(); i++) {
            removed = false;
            result = list.get(i);
            // 1 is an invalid choice if you are at an altitude >= nrAltitudes-1
            if (result.getValue1().get(0) == 1 && position.getValue0() >= nrAltitudes - 1) {
                list.remove(i);
                removed = true;
            }
            // -1 is an invalid choice if you ar at an altitude <= 1
            if (result.getValue1().get(0) == -1 && position.getValue0() <= 1) {
                list.remove(i);
                removed = true;
            }
            if (!removed && result.getValue0() > max) {
                max = result.getValue0();
            }
        }

        // Remove elements that do not have the max score
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getValue0() < max) {
                list.remove(i);
            }
        }

        // Select one of the elements that are left
        result = list.get((int )(Math.random() * list.size()));

        return result.setAt0(result.getValue0() + score);
    }

    private static Comparator<Pair<Integer, List<Integer>>> dfsComparator = new Comparator<Pair<Integer, List<Integer>>>() {
        @Override
        public int compare(Pair<Integer, List<Integer>> o1, Pair<Integer, List<Integer>> o2) {
            if (o1.getValue0().equals(o2.getValue0())) {
                int i = 0;
                while (i < o1.getValue1().size() && i < o2.getValue1().size()) {
                    if (!o1.getValue1().get(i).equals(o2.getValue1().get(i))) {
                        return o1.getValue1().get(i).compareTo(o2.getValue1().get(i));
                    }
                    i++;
                }
                return 0;
            }
            return o1.getValue0().compareTo(o2.getValue0());
        }
    };


    // =================================================================================================================
    // OTHER METHODS
    // =================================================================================================================

    private Triplet<Integer, Integer, Integer> nextPosition(Triplet<Integer, Integer, Integer> position) {
        if (!memoPosition.containsKey(position)) {
            Pair<Integer, Integer> vector = windVectors.get(position);
            Triplet<Integer, Integer, Integer> answer;
            if (position.getValue0() < 0 || position.getValue0() > nrAltitudes) {
                answer = null;
            }
            else if (position.getValue0() == 0) {
                answer = position;
            }
            else if (position.getValue1() + vector.getValue0() < 0 || position.getValue1() + vector.getValue0() >= nrRows) {
                answer = null;
            }
            else {
                answer = new Triplet<Integer, Integer, Integer>(position.getValue0(), position.getValue1() + vector.getValue0(),
                        (position.getValue2() + vector.getValue1() + nrColumns) % nrColumns);
            }
            memoPosition.put(position, answer);
            return answer;
        }
        return memoPosition.get(position);
    }

    private BitSet coveredTargetsByBalloon(int turn, Triplet<Integer, Integer, Integer> position) {
        BitSet bitset = new BitSet(nrTargets);
        if (position.getValue0() == 0) {
            return bitset;
        }
        for (int target = 0; target < nrTargets; target++) {
            if (covered(position, targets.get(target)) && !covered[turn].get(target)) {
                bitset.set(target);
            }
        }
        return bitset;
    }

    private boolean covered(Triplet<Integer, Integer, Integer> position, Pair<Integer, Integer> target) {
        if (position.getValue0() == 0) {
            return false;
        }
        return (pow(position.getValue1()-target.getValue0())) + pow(columnDist(position.getValue2(), target.getValue1())) <= pow(nrRadius);
    }

    private int columnDist(int colBalloon, int colTarget) {
        return Math.min(Math.abs(colBalloon - colTarget), nrColumns - Math.abs(colBalloon - colTarget));
    }

    private int pow(int num) {
        return num * num;
    }

    private int score() {
        int score = 0;
        for (int turn = 0; turn < nrTurns; turn++) {
            score += covered[turn].cardinality();
        }
        return score;
    }
}