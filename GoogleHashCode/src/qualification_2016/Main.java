package qualification_2016;

//import org.javatuples.Pair;
//import org.javatuples.Triplet;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.*;


// =====================================================================================================================
// MAIN
// =====================================================================================================================

public class Main {

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

        int in = sc.nextInt();
        sc.close();
        // END input

        while (true) {
            // Reset

            long startTime = System.currentTimeMillis();
            getResult();
            long endTime = System.currentTimeMillis();
            System.out.println("That took " + (endTime - startTime) + " milliseconds");

//            score = score();
//            System.out.println("Score: " + score + ", maxScore: " + maxScore + "\n");
//
//            if (score > maxScore) {
//                maxScore = score;
//                System.out.println("\n=======================\nNew max score: " + score
//                        + "\n=======================\n");
//                // Create string to write solution to file
//                StringBuilder builder = new StringBuilder();
//                // Write solution to builder
//
//                // Write solution to file
//                BufferedWriter out = new BufferedWriter(new FileWriter("output.txt"));
//                out.append(builder);
//                out.flush();
//                out.close();
//            }
        }
    }


    // =================================================================================================================
    // VARIABLES
    // =================================================================================================================




    // =================================================================================================================
    // SOLUTION
    // =================================================================================================================

    private void getResult() {

    }
}