class Solution4 {

    public static void main(String[] args) {
        new Solution4();
    }
    
    public Solution4() {
        System.out.println("Tact Coa: \t" + isPermutationOfPalindrome("Tact Coa"));
        System.out.println("Tact Coab: \t" + isPermutationOfPalindrome("Tact Coab"));
    }

    boolean isPermutationOfPalindrome(String s) {
        s = s.toLowerCase();

        // only a-z -> 26 bits needed
        int bitmap = 0;

        for (char c : s.toCharArray()) {
            int i = getNumericValue(c);
            if (i != -1) {
                bitmap = flip(bitmap, i);
            }
        }

        return (bitmap == 0) || (bitmap & (bitmap-1)) == 0;
    }

    // return 0 for a, ..., 25 for z and -1 for other characters
    int getNumericValue(char c) {
        int a = Character.getNumericValue('a');
        int z = Character.getNumericValue('z');

        int val = Character.getNumericValue(c);
        if (a <= val && val <= z) {
            return val - a;
        }
        return -1;
    }

    // flip the bit at index i in the bitmap
    int flip(int bitmap, int i) {
        int mask = (1 << i);
        if ((bitmap & mask) == 0) {
            bitmap |= mask;
        }
        else {
            bitmap &= ~mask;
        }
        return bitmap;
    }

}