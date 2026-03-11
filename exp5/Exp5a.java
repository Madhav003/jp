import java.util.Scanner;

class p5a {
    public static void main(String args[]){
        Scanner sc= new Scanner(System.in);
        System.out.println("Enter a paragraph:");
        String n=sc.nextLine();

        System.out.println("1)To Count total no. of Sentences and total no. of words in each Sentence:");

        int wordcount=0;
        int sentcount=0;
        int wordinsent=0;
        System.out.println("\nWord count in each sentence:");

        for (int i=0;i<n.length();i++) {
            char ch =n.charAt(i);
            if (ch==' ') {
                wordcount++;
                wordinsent++;
            }
            if (ch=='.'||ch=='!'||ch=='?') {
                sentcount++;
                wordinsent++;
                wordcount++;
                System.out.println("Sentence "+sentcount+" = "+wordinsent+" words");
                wordinsent= 0;
                
            }
        }
        System.out.println("\nTotal number of sentences: "+sentcount);
        System.out.println("Total number of words: "+wordcount);


        System.out.println("\n2)To count total no. of chars and occurrence of each char in entire paragraph:");

        int totalchar=n.length();
        System.out.println("\nTotal characters(incl spaces):"+ totalchar);
        System.out.println("\nCharacter Occurrence:");

        for(int i=0;i<n.length();i++){
            char curr=n.charAt(i);
            int count=0;
            for (int j=0;j<n.length();j++){
                if (curr==n.charAt(j)){
                    count++;
                }
            }
        
            if (n.indexOf(curr)==i) {
                System.out.println(curr+" : "+count);
            }
        }

        System.out.println("\n3)Search a word in paragraph and print the pos of word(if found):");
        System.out.println("\nEnter a word to search:");
        String search= sc.next();
        
        boolean found = false;
        int pos=n.indexOf(search);
        
        while (pos != -1) {
            boolean startOk = (pos == 0 || n.charAt(pos - 1) == ' ');
            boolean endOk = (pos + search.length() == n.length() || 
                    n.charAt(pos + search.length()) == ' ' ||
                    n.charAt(pos + search.length()) == '.' ||
                    n.charAt(pos + search.length()) == '!' ||
                    n.charAt(pos + search.length()) == '?');

            if (startOk && endOk) {
            System.out.println("Word found at position: " + pos);
            found = true;
            }

             pos = n.indexOf(search, pos + search.length());
            }

            if (!found) {
                System.out.println("Word not found in paragraph.");
        }
       sc.close();
    }
}
