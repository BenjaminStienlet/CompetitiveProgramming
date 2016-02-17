public class Solution5 {
    
    public static void main(String[] args) {
        new Solution5();
    }

    public Solution5() {
        System.out.println(multiply(13, 13));
    }

    public int multiply(int n, int m) {
        return multiplyHelper(Math.min(n, m), Math.max(n, m));
    }

    private int multiplyHelper(int n, int m) {
        if (n == 1) {
            return m;
        }
        int power = (int) (Math.log(n) / Math.log(2));
        return (m << power) + multiplyHelper(n - (1 << power), m);
    }
}