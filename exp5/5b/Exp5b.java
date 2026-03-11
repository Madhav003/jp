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

        for (int i = 0; i < w.length - 1; i++) {
            for (int j = 0; j < w.length - 1 - i; j++) {
                boolean swap = false;
                if (w[j].length() < w[j+1].length()) swap = true;
                if (w[j].length() == w[j+1].length() && w[j].compareTo(w[j+1]) > 0) swap = true;
                if (swap) {
                    String t = w[j];
                    w[j] = w[j+1];
                    w[j+1] = t;
                }
            }
        }

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
