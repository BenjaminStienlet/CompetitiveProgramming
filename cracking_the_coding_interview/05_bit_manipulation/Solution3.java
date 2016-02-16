import java.util.ArrayList;

public class Solution3 {
    
    public static void main(String[] args) {
        new Solution3();
    }

    public Solution3() {
        System.out.println(flipToWin(1775));
        System.out.println(flipToWin(-1));
        System.out.println(flipToWin((1 << 31) - 1));
    }

    private static final int SEQUENCE_LENGTH = 32;

    public int flipToWin(int sequence) {
        System.err.println(Integer.toBinaryString(sequence));
        ArrayList<Integer> frequencies = createFrequencyList(sequence);

        int max = 0;
        for (int i = 1; i < frequencies.size(); i += 2) {
            if (i+2 < frequencies.size() && frequencies.get(i+1) == 1) {
                // only 1 zero between this and the next sequence of 1s
                max = Math.max(max, frequencies.get(i) + 1 + frequencies.get(i+2));
            } else if (frequencies.get(i-1) > 0 || 
                (i+1 < frequencies.size() && frequencies.get(i+1) > 0)) {
                // there is a zero next to this sequence that can be flipped
                max = Math.max(max, frequencies.get(i) + 1);
            } else {
                max = Math.max(max, frequencies.get(i));
            }
        }
        return max;
    }

    // frequencies, first for zero, then one and so on
    private ArrayList<Integer> createFrequencyList(int sequence) {
        ArrayList<Integer> frequencies = new ArrayList<Integer>();
        boolean oneBit = false;
        int count = 0;

        for (int i = 0; i < SEQUENCE_LENGTH; i++) {
            if (getBit(sequence, i) == oneBit) {
                count++;
            }
            else {
                frequencies.add(count);
                count = 1;
                oneBit = !oneBit;
            }
        }

        frequencies.add(count);
        return frequencies;
    }

    private boolean getBit(int sequence, int index) {
        return (sequence & (1 << index)) != 0;
    }

}