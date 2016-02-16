public class Solution1 {
        
    public static void main(String[] args) {
        new Solution1();
    }

    public Solution1() {
        System.out.println("3: " + runUpStairs(3));
        System.out.println("15: " + runUpStairs(15));
    }

    public int runUpStairs(int n) {
        int[] ways = new int[n+1];
        int[] steps = new int[] {1, 2, 3};
        ways[0] = 1;
        for (int i = 1; i <= n; i++) {
            for (int step : steps) {
                if (i - step >= 0) {
                    ways[i] += ways[i-step];
                }
            }
        }
        return ways[n];
    }
}