import java.util.Scanner;

public class Exp5a {

    public static void main(String[] args){

        Scanner sc=new Scanner(System.in);

        System.out.println("Enter a paragraph: ");
        String para=sc.nextLine();

        int twcount=0;
        int tscount=0;
        char prev=' ';
        boolean space = false;

        for(int i=0;i<para.length();i++){
            char c=para.charAt(i);
            if((c=='?' || c=='!' || c=='.') && (prev!='?' && prev!='.' && prev!='!')){
                tscount++;
            }

            boolean currsep=(c==' ' || c=='.'|| c=='!' || c== '?');

            if (!currsep){
                if (!space){
                    twcount++;
                    space=true;
                }

            } else {
                space=false;
            }

            prev=c;
        }

        System.out.println("The total number of sentences in the paragraph are: " + tscount);
        System.out.println("The total number of words in the paragraph are: " + twcount);
        System.out.println("Now counting the number of words in each sentence:");

        int words=0;
        int sentenceno=1;
        prev=' ';
        space = false;


        for(int i=0;i<para.length();i++){
            char c=para.charAt(i);
            boolean currsep = (c == ' ' || c == '.' || c == '!' || c == '?');


            if (!currsep) {
                if (!space) {
                    words++;
                    space = true;
                }
            } else {
                space = false;
            }

            if(c=='?' || c=='!' || c=='.'){
                if(prev!='?' && prev!= '!' && prev!= '.') {
                    System.out.println("The sentence " + sentenceno + " has " + words + " words.");
                    sentenceno++;
                    words = 0;
                }
            }
            prev=c;
        }

        System.out.println("Now counting the occurance of each character: ");

        int count[]=new int[256];
        int totalcharacters = 0;

        for(int i=0;i<para.length();i++){
            if(para.charAt(i) != ' '){
                totalcharacters++;
            }
        }

        System.out.println("Total characters: " + totalcharacters);

        for(int i=0;i<para.length();i++){
            char c=para.charAt(i);
            count[c]++;
        }

        for(int i=0;i<256;i++){
            if(count[i]!=0){
                System.out.println("The character " + (char)i + " has occured " + count[i] + " times");
            }
        }

        System.out.println("Enter the word you want to search: ");
        String searchword=sc.nextLine();

        int idx=para.indexOf(searchword);

        if(idx!=-1){
            System.out.println("word is found at index: " + idx);
        }
        else{
            System.out.println("word is not found");
        }
    }
}
