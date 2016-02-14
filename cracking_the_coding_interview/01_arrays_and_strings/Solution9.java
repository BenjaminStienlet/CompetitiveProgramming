class Solution9 {
    
    public static void main(String[] args) {
        new Solution9();
    }

    public Solution9() {
        System.out.println("Valid example: " + isStringRotation("waterbottle", "erbottlewat"));
    }

    public boolean isStringRotation(String str1, String str2) {
        if (str1.length() != str2.length()) {
            return false;
        }
        return str1.concat(str1).contains(str2);
    }

}