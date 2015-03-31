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

// =====================================================================================================================
// MAIN
// =====================================================================================================================


public class Main {

    int maxDepth = 3;

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

        solution = new int[nrTurns][nrBalloons];

        // Add startCell to balloon positions at turn -1
        balloonPosition = new HashMap<Pair<Integer, Integer>, Triplet<Integer, Integer, Integer>>();
        for (int i = 0; i < nrBalloons; i++) {
            balloonPosition.put(new Pair<Integer, Integer>(i, -1), startCell);
        }

        // Create an bitset with nrTargets zeroes for every turn
        covered = new BitSet[nrTurns];
        for (int i = 0; i < nrTurns; i++) {
            covered[i] = new BitSet(nrTargets);
        }

        memoPosition = new HashMap<Triplet<Integer, Integer, Integer>, Triplet<Integer, Integer, Integer>>();

        long startTime = System.currentTimeMillis();

        getResult();

        long endTime = System.currentTimeMillis();
        System.out.println("That took " + (endTime - startTime) + " milliseconds");

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

        out.append(builder);

        out.flush();
        out.close();
        sc.close();
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

        int score_min1, score_0, score_plus1;
        Triplet<Integer, Integer, Integer> position;
        Triplet<Integer, Integer, Integer> newPosition;
        boolean liftOff;

        for (int balloon = 0; balloon < nrBalloons; balloon++) {
            System.out.println("Balloon " + balloon);
            liftOff = false;

            for (int turn = 0; turn < nrTurns; turn++) {

                position = balloonPosition.get(new Pair<Integer, Integer>(balloon, turn-1));

                if (position.equals(outsideField)) {
                    balloonPosition.put(new Pair<Integer, Integer>(balloon, turn), outsideField);
                    solution[turn][balloon] = 0;
                    continue;
                }

                // -1
                newPosition = nextPosition(position.setAt0(position.getValue0() - 1));
                if (newPosition == null)
                    score_min1 = Integer.MIN_VALUE;
                else
                    score_min1 = dfs(turn, newPosition, liftOff, 0);

                // 0
                newPosition = nextPosition(position);
                if (newPosition == null)
                    score_0 = Integer.MIN_VALUE;
                else
                    score_0 = dfs(turn, newPosition, liftOff, 0);

                // +1
                newPosition = nextPosition(position.setAt0(position.getValue0() + 1));
                if (newPosition == null)
                    score_plus1 = Integer.MIN_VALUE;
                else
                    score_plus1 = dfs(turn, newPosition, liftOff, 0);

                // Pick the one with the highest score
                // Preference for 1
                if (score_min1 > score_0 && score_min1 > score_plus1 && position.getValue0() > 1) {
                    solution[turn][balloon] = -1;
                    newPosition = nextPosition(position.setAt0(position.getValue0() - 1));
                }
                else if (score_plus1 >= score_0 && score_plus1 >= score_min1 && position.getValue0() < nrAltitudes-1) {
                    solution[turn][balloon] = 1;
                    newPosition = nextPosition(position.setAt0(position.getValue0() + 1));
                }
                else { // TODO: als niet stijgen
                    solution[turn][balloon] = 0;
                    liftOff = true;
                    newPosition = nextPosition(position);
                }

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

    private int dfs(int turn, Triplet<Integer, Integer, Integer> position, boolean liftOff, int depth) {

        if (position.getValue0() < 0 || (position.getValue0() == 0 && liftOff)) {
            return Integer.MIN_VALUE;
        }
        if (position.getValue0() == 0) {
            return 0;
        }

        int score = coveredTargetsByBalloon(turn, position).cardinality();
        if (depth >= maxDepth || turn >= nrTurns-1) {
            return score;
        }

        int score_min1, score_0, score_plus1;
        Triplet<Integer, Integer, Integer> newPosition;

        // -1
        newPosition = nextPosition(position.setAt0(position.getValue0() - 1));
        if (newPosition == null)
            score_min1 = Integer.MIN_VALUE; // TODO: score = - weight*(nrTurns - turn)
        else
            score_min1 = dfs(turn+1, newPosition, liftOff, depth+1);

        // 0
        newPosition = nextPosition(position);
        if (newPosition == null)
            score_0 = Integer.MIN_VALUE;
        else
            score_0 = dfs(turn+1, newPosition, true, depth+1);

        // +1
        newPosition = nextPosition(position.setAt0(position.getValue0() + 1));
        if (newPosition == null)
            score_plus1 = Integer.MIN_VALUE;
        else
            score_plus1 = dfs(turn+1, newPosition, liftOff, depth+1);

        return score + Math.max(score_0, Math.max(score_min1, score_plus1));
    }


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
        for (int target = 0; target < nrTargets; target++) {
            if (covered(position, targets.get(target)) && !covered[turn].get(target)) {
                bitset.set(target);
            }
        }
        return bitset;
    }

    private boolean covered(Triplet<Integer, Integer, Integer> position, Pair<Integer, Integer> target) {
        return (pow(position.getValue1()-target.getValue0())) + pow(columnDist(position.getValue2(), target.getValue1())) <= pow(nrRadius);
    }

    private int columnDist(int colBalloon, int colTarget) {
        return Math.min(Math.abs(colBalloon - colTarget), nrColumns - Math.abs(colBalloon - colTarget));
    }

    private int pow(int num) {
        return num * num;
    }
}