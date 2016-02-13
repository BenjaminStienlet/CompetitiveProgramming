import java.util.Set;
import java.util.HashSet;

import java.util.Arrays;

class Solution1 {

    public static void main(String[] args) {
        new Solution1();
    }

    public Solution1() {
        System.out.println("Valid example: " + isUnique("abc"));
        System.out.println("Invalid example: " + isUnique("aba"));
        System.out.println("=====");
        System.out.println("Valid example: " + isUnique2("abc"));
        System.out.println("Invalid example: " + isUnique2("aba"));
    }

    // Check whether the given string has all unique characters
    // HashSet: O(N) time
    private boolean isUnique(String str) {
        Set<Character> alreadySeen  = new HashSet<Character>();
        for (char c : str.toCharArray()) {
            if (alreadySeen.contains(c)) {
                return false;
            } else {
                alreadySeen.add(c);
            }
        }
        return true;
    }

    // No additional data structures
    // Sort string: O(N log(N)) time
    private boolean isUnique2(String str) {
        char[] chars = str.toCharArray();
        Arrays.sort(chars);
        
        char previous;
        if (chars.length > 0) {
            previous = chars[0];
        } else {
            return true;
        }

        for (int i = 1; i < chars.length; i++) {
            if (chars[i] == previous) {
                return false;
            } else {
                previous = chars[i];
            }
        }

        return true;
    }

}