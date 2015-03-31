package final_2014;

import org.javatuples.Pair;

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

        nrRows = sc.nextInt();          // R
        nrColumns = sc.nextInt();       // C
        nrAltitudes = sc.nextInt();     // A
        nrTargets = sc.nextInt();       // L
        nrRadius = sc.nextInt();        // V
        nrBalloons = sc.nextInt();      // B
        nrTurns = sc.nextInt();         // T

        int[] startCell = new int[2];
        startCell[0] = sc.nextInt();
        startCell[1] = sc.nextInt();

        // Read targets
        targets = new int[nrTargets][2];
        for (int i = 0; i < nrTargets; i++) {
            targets[i][0] = sc.nextInt();
            targets[i][1] = sc.nextInt();
        }

        // Read windvectors
        windVectors = new int[nrAltitudes+1][nrRows][nrColumns][2];
        for (int i = 1; i <= nrAltitudes; i++) {
            for (int j = 0; j < nrRows; j++) {
                for (int k = 0; k < nrColumns; k++) {
                    windVectors[i][j][k][0] = sc.nextInt();
                    windVectors[i][j][k][1] = sc.nextInt();
                }
            }
        }

        solution = new int[nrTurns][nrBalloons];

        // Add startCell to balloon positions at turn -1
        balloonPosition = new HashMap<Pair<Integer, Integer>, Integer[]>();
        for (int i = 0; i < nrBalloons; i++) {
            balloonPosition.put(new Pair<Integer, Integer>(i, -1), new Integer[]{0, startCell[0], startCell[1]});
        }

        // Create an bitset with nrTargets zeroes for every turn
        covered = new BitSet[nrTurns];
        for (int i = 0; i < nrTurns; i++) {
            covered[i] = new BitSet(nrTargets);
        }

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

    int[][] targets;    // [nrTargets][2]
    int[][] solution;   // [Turn][Balloon] -> -1, 0, +1
    int[][][][] windVectors;    // [Altitude][Row][Column][2] -> a, b
    Map<Pair<Integer, Integer>, Integer[]> balloonPosition; // [Balloon, Turn] : [Altitude, Row, Column]
    BitSet[] covered;   // [nrTurns], length nrTargets

    // SCORES
    // maxDepth : score, time
    // 3 : 201369, 16s
    // 4 : 167155, 45s
    // 5 : 116197, 130s
    // 6 : 434405, 389s
    // 7 : 75115, 1152s
    int maxDepth = 3;

    Integer[] outsideField = new Integer[]{-1, -1, -1};


    // =================================================================================================================
    // SOLUTION
    // =================================================================================================================

    private void getResult() {

        int score_min1, score_0, score_plus1;
        Integer[] position;
        int[] newPosition;
        boolean liftOff;

        for (int balloon = 0; balloon < nrBalloons; balloon++) {
            System.out.println("Balloon " + balloon);
            liftOff = false;

            for (int turn = 0; turn < nrTurns; turn++) {

                position = balloonPosition.get(new Pair<Integer, Integer>(balloon, turn-1));

                if (Arrays.equals(position, outsideField)) {
                    balloonPosition.put(new Pair<Integer, Integer>(balloon, turn), outsideField);
                    solution[turn][balloon] = 0;
                    continue;
                }

                // -1
                newPosition = nextPosition(position[0] - 1, position[1], position[2]);
                if (newPosition == null)
                    score_min1 = Integer.MIN_VALUE;
                else
                    score_min1 = dfs(turn, newPosition[0], newPosition[1], newPosition[2], liftOff, 0);

                // 0
                newPosition = nextPosition(position[0], position[1], position[2]);
                if (newPosition == null)
                    score_0 = Integer.MIN_VALUE;
                else
                    score_0 = dfs(turn, newPosition[0], newPosition[1], newPosition[2], liftOff, 0);

                // +1
                newPosition = nextPosition(position[0] + 1, position[1], position[2]);
                if (newPosition == null)
                    score_plus1 = Integer.MIN_VALUE;
                else
                    score_plus1 = dfs(turn, newPosition[0], newPosition[1], newPosition[2], liftOff, 0);

                // Pick the one with the highest score
                if (score_0 >= score_min1 && score_0 >= score_plus1) {
                    solution[turn][balloon] = 0;
                    liftOff = true;
                    newPosition = nextPosition(position[0], position[1], position[2]);
                }
                else if (score_min1 >= score_0 && score_min1 >= score_plus1) {
                    solution[turn][balloon] = -1;
                    newPosition = nextPosition(position[0] - 1, position[1], position[2]);
                }
                else {
                    solution[turn][balloon] = 1;
                    newPosition = nextPosition(position[0] + 1, position[1], position[2]);
                }

                // Set the balloon position
                if (newPosition == null) {
                    balloonPosition.put(new Pair<Integer, Integer>(balloon, turn), outsideField);
                }
                else {
                    balloonPosition.put(new Pair<Integer, Integer>(balloon, turn),
                            new Integer[]{newPosition[0], newPosition[1], newPosition[2]});
                    covered[turn].or(coveredTargetsByBalloon(turn, newPosition[1], newPosition[2]));
                }
            }
        }

    }

    private int dfs(int turn, int altBalloon, int rowBalloon, int colBalloon, boolean liftOff, int depth) {

        if (altBalloon < 0 || (altBalloon == 0 && liftOff)) {
            return Integer.MIN_VALUE;
        }
        if (altBalloon == 0) {
            return 0;
        }

        int score = coveredTargetsByBalloon(turn, rowBalloon, colBalloon).cardinality();
        if (depth >= maxDepth || turn >= nrTurns-1) {
            return score;
        }

        int score_min1, score_0, score_plus1;
        int[] newPosition;

        // -1
        newPosition = nextPosition(altBalloon - 1, rowBalloon, colBalloon);
        if (newPosition == null)
            score_min1 = Integer.MIN_VALUE; // TODO: score = - weight*(nrTurns - turn)
        else
            score_min1 = dfs(turn+1, altBalloon - 1, newPosition[1], newPosition[2], liftOff, depth+1);

        // 0
        newPosition = nextPosition(altBalloon, rowBalloon, colBalloon);
        if (newPosition == null)
            score_0 = Integer.MIN_VALUE;
        else
            score_0 = dfs(turn+1, altBalloon, newPosition[1], newPosition[2], true, depth+1);

        // +1
        newPosition = nextPosition(altBalloon + 1, rowBalloon, colBalloon);
        if (newPosition == null)
            score_plus1 = Integer.MIN_VALUE;
        else
            score_plus1 = dfs(turn+1, altBalloon + 1, newPosition[1], newPosition[2], liftOff, depth+1);

        return score + Math.max(score_0, Math.max(score_min1, score_plus1));
    }


    // =================================================================================================================
    // OTHER METHODS
    // =================================================================================================================

    private int[] nextPosition(int alt, int row, int col) {
        if (alt < 0 || alt > nrAltitudes) {
            return null;
        }
        if (alt == 0) {
            return new int[]{alt, row, col};
        }
        int[] vector = windVectors[alt][row][col];
        if (row + vector[0] < 0 || row + vector[0] >= nrRows) {
            return null;
        }
        return new int[]{alt, row + vector[0], (col + vector[1] + nrColumns) % nrColumns};
    }

    private BitSet coveredTargetsByBalloon(int turn, int rowBalloon, int colBalloon) {
        BitSet bitset = new BitSet(nrTargets);
        for (int target = 0; target < nrTargets; target++) {
            if (covered(rowBalloon, colBalloon, targets[target][0], targets[target][1]) && !covered[turn].get(target)) {
                bitset.set(target);
            }
        }
        return bitset;
    }

    private boolean covered(int rowBalloon, int colBalloon, int rowTarget, int colTarget) {
        return (pow(rowBalloon-rowTarget)) + pow(columnDist(colBalloon, colTarget)) <= pow(nrRadius);
    }

    private int columnDist(int colBalloon, int colTarget) {
        return Math.min(Math.abs(colBalloon - colTarget), nrColumns - Math.abs(colBalloon - colTarget));
    }

    private int pow(int num) {
        return num * num;
    }
}