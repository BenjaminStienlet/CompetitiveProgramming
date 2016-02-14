class Solution6 {
    
    public static void main(String[] args) {
        new Solution6();
    }

    public Solution6() {
        System.out.println(compress("aabccccaaa"));
        System.out.println(compress("abc"));
    }

    // Compress a string using the counts of repeated characters
    public String compress(String str) {
        if (str.length() == 0) {
            return str;
        }
        StringBuilder compressed = new StringBuilder();
        char previous = str.charAt(0);
        int count = 1;

        for (int i = 1; i < str.length(); i++) {
            if (str.charAt(i) != previous) {
                compressed.append(previous);
                compressed.append(count);
                previous = str.charAt(i);
                count = 1;
            } else {
                count++;
            }
        }
        compressed.append(previous);
        compressed.append(count);

        String compressedString = compressed.toString();
        if (compressedString.length() < str.length()) {
            return compressedString;
        } else {
            return str;
        }
    }

}