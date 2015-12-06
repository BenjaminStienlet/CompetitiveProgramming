import java.util.BitSet;
import java.util.Scanner;
import java.io.FileReader;
import java.io.FileNotFoundException;

public class Solution7 {
    
    public static void main(String[] args) {
        new Solution7();
    }

    public Solution7() {
        try {
            System.out.println(findOpenInt("input.txt"));
        }
        catch (FileNotFoundException e) {
            // report
        }
    }

    public int findOpenInt(String filename) throws FileNotFoundException {
        Scanner in = new Scanner(new FileReader(filename));
        BitSet bitset = new BitSet(Integer.MAX_VALUE);
        
        while(in.hasNextInt()) {
            bitset.set(in.nextInt());
        }

        return bitset.nextClearBit(0);
    }

}