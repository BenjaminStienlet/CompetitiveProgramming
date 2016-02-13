import java.util.Map;
import java.util.HashMap;
import java.util.HashSet;

class Solution2 {

    public static void main(String[] args) {
        new Solution2();
    }

    public Solution2() {
        System.out.println("Valid example: " + isPermutation("abc", "cba"));
        System.out.println("Invalid example: " + isPermutation("abc", "abcde"));
    }

    // Check whether a string is a permutation of an other string
    private boolean isPermutation(String str1, String str2) {
        if (str1.length() != str2.length()) {
            return false;
        }
        Map<Character, Integer> map1 = getFrequencyMap(str1);
        Map<Character, Integer> map2 = getFrequencyMap(str2);

        HashSet<Character> keySet = new HashSet(map1.keySet());
        keySet.addAll(map2.keySet());
        for (char c : keySet) {
            if (!map1.containsKey(c) || !map2.containsKey(c) || 
                    map1.get(c) != map2.get(c)) {
                return false;
            }
        }
        return true;
    }

    // Returns a map with the frequency of every character in the string
    private Map<Character, Integer> getFrequencyMap(String str) {
        Map<Character, Integer> freqMap = new HashMap<Character, Integer>();

        for (char c : str.toCharArray()) {
            if (freqMap.containsKey(c)) {
                freqMap.put(c, freqMap.get(c) + 1);
            } else {
                freqMap.put(c, 1);
            }
        }

        return freqMap;
    }

}