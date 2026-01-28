import java.util.Scanner;
class Exp2cii{
  public static void main(String args[]){
    Scanner sc = new Scanner(System.in);
    System.out.print("Enter: ");
    char ch = sc.next().charAt(0);
    if((ch >= 'A' && ch <= 'Z') || (ch >= 'a' && ch <= 'z')){
        System.out.println("Alphabet character");
    }
    if(ch >= '0' && ch <= '9'){
        System.out.println("Digit character");
    }
    if(ch>=0 && ch<=47||ch>=58 && ch <= 64 || ch>=91 && ch<=96 || ch>=123 && ch<=127){
        System.out.println("Special character");
    }
    sc.close();
}
}
