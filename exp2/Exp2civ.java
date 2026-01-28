import java.util.Scanner;
class Exp2civ{
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter number: ");
        int num = sc.nextInt();
        if(num<0){
            System.out.println("Palindrome not applicable");
            return;
        }
        int temp = num;
        int a;
        int numn;
        a = temp%10;
        temp = temp/10;
        numn = a;
        while(temp>0){
            a = temp%10;
            temp = temp/10;
            numn = (numn*10) + a;
        }
        System.out.println("Normal: " + num);
        System.out.println("Reversed: " + numn);
        if(num==numn) System.out.println("It is a palindrome");
        else System.out.println("Not a palindrome");
    }
}