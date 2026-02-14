
import java.util.Scanner;

class Exp5a {
    public static void main(String arg[]) {

        int sCount = 0;
        int cCount = 0;
        int wCount = 0;

        int freq[] = new int[256];

        Scanner sc = new Scanner(System.in);
        System.out.println("Enter a paragraph:");
        String para = sc.nextLine();

        for (int i = 0; i < para.length(); i++) {
            char ch = para.charAt(i);
            int chn = ch;
            freq[chn]++;

            if (chn > 64 && chn < 123) {
                cCount++;
            }

            if (ch == ' ') {
                wCount++;
            }

            if (ch == '.' || ch == '!' || ch == '?') {
                System.out.println("Sentence " + (sCount + 1));
                System.out.println("Characters : " + cCount);
                System.out.println("Words : " + (wCount + 1));
                System.out.println();

                sCount++;
                cCount = 0;
                wCount = 0;
            }
        }

        System.out.println("Number of sentences: " + sCount);

        System.out.println("\nCharacter frequencies:");
        for (int i = 0; i < 256; i++) {
            if (freq[i] > 0) {
                System.out.println("'" + (char)i + "' : " + freq[i]);
            }
        }

        System.out.println("\nEnter a word to search:");
        String word = sc.nextLine();

        int idx = para.indexOf(word);

        if (idx != -1) {
            System.out.println("Word found at index: " + idx);
        } else {
            System.out.println("Word not found");
        }

        sc.close();
    }
}

