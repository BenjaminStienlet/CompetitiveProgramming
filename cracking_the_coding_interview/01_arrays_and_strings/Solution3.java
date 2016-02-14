class Solution3 {
    
    public static void main(String[] args) {
        new Solution3();
    }

    public Solution3() {
        String str = new String(urlify("Mr John Smith    ".toCharArray(), 13));
        System.out.println("Result: " + str);
    }

    public char[] urlify(char[] str, int length) {
        int trailingSpaces = str.length - length;
        int nrSpaces = countOccurrences(str, ' ') - trailingSpaces;
        int nrChars = length - nrSpaces;
        
        int i = nrChars + 3*nrSpaces - 1;
        int j = nrChars + nrSpaces - 1;

        while (j >= 0) {
            if (str[j] == ' ') {
                str[i--] = '0';
                str[i--] = '2';
                str[i--] = '%';
            } else {
                str[i--] = str[j];
            }
            j--;
        }

        return str;
    }

    public int countOccurrences(char[] list, char searchEl) {
        int count = 0;
        for (char el : list) {
            if (el == searchEl) {
                count++;
            }
        }
        return count;
    }
}