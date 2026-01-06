import java.util.Scanner;
class Exp2ciii{
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter number: ");
        int num = sc.nextInt();
        for(int i = 2; i<(num/2); i++){
            if(num%i == 0){
                System.out.println("Not a prime number");
                return;
            }
            else continue;
        }
        System.out.println("Prime number");
        sc.close();
    }
}