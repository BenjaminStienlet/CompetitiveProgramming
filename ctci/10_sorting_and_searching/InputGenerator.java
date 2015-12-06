import java.lang.Math;
import java.util.Random;
import java.io.Writer;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.FileOutputStream;
import java.io.IOException;

public class InputGenerator {
    
    public static void main(String[] args) {
        Random rand = new Random();
        Writer writer = null;
        try {
            writer = new BufferedWriter(
                        new OutputStreamWriter(
                        new FileOutputStream("input.txt"), "utf-8"));
            for (long i = 0; i < Math.pow(2, 32); i++) {
                writer.write(Integer.toString(rand.nextInt(Integer.MAX_VALUE)) + "\n");
            }
        } catch (IOException ex) {
          // report
        } finally {
           try {writer.close();} catch (Exception ex) {/*ignore*/}
        }
    }

}