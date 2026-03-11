import java.util.*;
import java.io.*;

public class Exp5b {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter string: ");
        String s = sc.nextLine();

        s = s.replace(".", "");
        String[] w = s.trim().split("\\s+");

        for (int i = 0; i < w.length; i++) {
            w[i] = w[i].substring(0, 1).toUpperCase() + w[i].substring(1).toLowerCase();
        }

        Arrays.sort(w, (a, b) -> {
            if (b.length() != a.length()) return b.length() - a.length();
            return a.compareTo(b);
        });

        String r = "";
        for (int i = 0; i < w.length; i++) {
            if (i == 0) r = w[i];
            else r = r + " " + w[i];
        }
        r = r + ".";

        System.out.println(r);

        try {
            FileWriter f = new FileWriter("output.txt");
            f.write(r);
            f.close();
            System.out.println("Saved to output.txt");
        } catch (Exception e) {
            System.out.println("Error saving file.");
        }
    }
}
