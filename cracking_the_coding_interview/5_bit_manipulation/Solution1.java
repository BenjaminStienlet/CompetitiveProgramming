
public class Solution1 {
    
    public static void main(String[] args) {
        new Solution1();
    }

    public Solution1() {
        int x = insert(2047, 19, 2, 6);
        System.out.println(Integer.toString(x, 2));
    }

    // Insert m in n at positions i..j
    public int insert(int n, int m, int i, int j) {
        // Clear i..j in n
        int mask = ~(((1 << j+1) - 1) ^ ((1 << i) - 1)); // 1..10000011
        int n_cleared = n & mask;

        // Shift m
        int m_shifted = m << i;

        // Combine the two
        return n_cleared | m_shifted;
    }

}