class Solution5 {
    
    public static void main(String[] args) {
        new Solution5();
    }

    public Solution5() {
        System.out.println("Valid example: " + oneWay("pale", "ple"));
        System.out.println("Valid example: " + oneWay("pales", "pale"));
        System.out.println("Valid example: " + oneWay("pale", "bale"));
        System.out.println("Invalid example: " + oneWay("pale", "bake"));
    }

    // Check whether two strings are one edit away
    public boolean oneWay(String str1, String str2) {
        if (str1.length() + 1 == str2.length()) {
            // insert in str1
            return oneWayRemove(str2, str1);
        } else if (str1.length() == str2.length() + 1) {
            // remove from str1
            return oneWayRemove(str1, str2);
        } else if (str1.length() == str2.length()) {
            // replace in str1
            return oneWayReplace(str1, str2);
        } 
        return false;
    }

    public boolean oneWayRemove(String str1, String str2) {
        if (str1.length() != str2.length() + 1) {
            return false;
        }
        int index1 = 0;
        int index2 = 0;
        while(index1 < str1.length() && index2 < str2.length()) {
            if (str1.charAt(index1) != str2.charAt(index2)) {
                if (index1 != index2) {
                    return false;
                }
                index1++;
            } else {
                index1++;
                index2++;
            }
        }
        return true;
    }

    public boolean oneWayReplace(String str1, String str2) {
        if (str1.length() != str2.length()) {
            return false;
        }
        boolean replaced = false;
        for (int i = 0; i < str1.length(); i++) {
            if (str1.charAt(i) != str2.charAt(i)) {
                if (replaced) {
                    return false;
                } else {
                    replaced = true;
                }
            }
        }
        return replaced;
    }

}