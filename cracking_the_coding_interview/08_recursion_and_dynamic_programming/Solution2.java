import java.util.LinkedList;

public class Solution2 {
    
    public static void main(String[] args) {
        new Solution2();
    }

    public Solution2() {
        boolean[][] grid = new boolean[][] 
            { {true, true, false, true}
            , {false, true, true, false}
            , {false, true, true, false}
            , {true, false, true, true}
            };
        shortestPath(grid);
    }

    public LinkedList<CoordinatePair> shortestPath(boolean[][] grid) {
        int[][] pathLength = new int[grid.length][grid[0].length];
        CoordinatePair[][] backtrack = new CoordinatePair[grid.length][grid[0].length];
        
        // Init first column
        for (int i = 1; i < grid.length; i++) {
            pathLength[i][0] = pathLength[i-1][0];
            backtrack[i][0] = new CoordinatePair(i-1, 0);
        }
        
        // Init first row
        for (int i = 1; i < grid[0].length; i++) {
            pathLength[0][i] = pathLength[0][i-1];
            backtrack[0][i] = new CoordinatePair(0, i-1);
        }
        
        for (int i = 1; i < grid.length; i++) {
            for (int j = 1; j < grid[i].length; j++) {
                if (!grid[i-1][j] && grid[i][j-1]) {
                    pathLength[i][j] = pathLength[i][j-1] + 1;
                    backtrack[i][j] = new CoordinatePair(i, j-1);
                } else if (grid[i-1][j] && !grid[i][j-1]) {
                    pathLength[i][j] = pathLength[i-1][j] + 1;
                    backtrack[i][j] = new CoordinatePair(i-1, j);
                } else if (!grid[i-1][j] && !grid[i][j-1]) {
                    grid[i][j] = false; // unreachable
                } else {
                    if (pathLength[i-1][j] < pathLength[i][j-1]) {
                        pathLength[i][j] = pathLength[i-1][j] + 1;
                        backtrack[i][j] = new CoordinatePair(i-1, j);
                    } else {
                        pathLength[i][j] = pathLength[i][j-1] + 1;
                        backtrack[i][j] = new CoordinatePair(i, j-1);
                    }
                }
            }
        }

        return generatePath(backtrack);
    }

    private LinkedList<CoordinatePair> generatePath(CoordinatePair[][] backtrack) {
        LinkedList<CoordinatePair> path = new LinkedList<CoordinatePair>();
        int r = backtrack.length-1;
        int c = backtrack[0].length-1;
        path.add(new CoordinatePair(r, c));

        while (backtrack[r][c] != null) {
            CoordinatePair pair = backtrack[r][c];
            path.add(pair);
            r = pair.r;
            c = pair.c;
        }
        return path;
    }

    private class CoordinatePair {
        public int r;
        public int c;

        public CoordinatePair(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
}