import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {
        new Main();
    }

    ArrayList<String> outlets;
    ArrayList<String> devices;

    public Main() throws IOException {
        Scanner sc = new Scanner(System.in);
        BufferedWriter out = new BufferedWriter(new FileWriter("output.txt"));
        int test_cases = sc.nextInt();
        for (int test_case = 1; test_case <= test_cases; test_case++) {
            System.out.println(test_case);
            int n = sc.nextInt();
            int l = sc.nextInt();
            outlets = new ArrayList<String>();
            devices = new ArrayList<String>();
            for (int i = 0; i < n; i++){
                outlets.add(sc.next());
            }
            for (int i = 0; i < n; i++){
                devices.add(sc.next());
            }
            out.write("Case #" + test_case + ": " + getResult(l) + "\n");
        }
        out.flush();
        out.close();
        sc.close();
    }

    public String getResult(int l) {
        ArrayList<Boolean> sequence = new ArrayList<Boolean>();
        int result = dp(sequence, l, 0);
        if (result == Integer.MAX_VALUE) {
            return "NOT POSSIBLE";
        }
        return "" + result;
    }

    public int dp(ArrayList<Boolean> sequence, int length, int nr) {
        if (sequence.size() == length) {
            ArrayList<String> out = new ArrayList<String>();
            for (String outlet : outlets) {
                String outlet2 = "";
                for (int i = 0; i < length; i++) {
                    if (sequence.get(i)) {
                        if (outlet.charAt(i) == '0') {
                            outlet2 += "1";
                        } else {
                            outlet2 += "0";
                        }
                    }
                    else {
                        outlet2 += outlet.charAt(i);
                    }
                }
                out.add(outlet2);
            }
            for (String device : devices) {
                if (out.contains(device)) {
                    out.remove(device);
                }
                else {
                    return Integer.MAX_VALUE;
                }
            }
            return nr;
        }
        ArrayList<Boolean> seqClone = (ArrayList<Boolean>) sequence.clone();
        sequence.add(true);
        seqClone.add(false);
        int flip = dp(sequence, length, nr+1);
        int no_flip = dp(seqClone, length, nr);
        if (flip < no_flip) {
            return flip;
        }
        return no_flip;
    }
}