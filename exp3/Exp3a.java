import java.util.Scanner;

class Exp3a{
  public static void main(String args[]){
    int ans = 0;
    Scanner sc = new Scanner(System.in);
    while(true){
    System.out.println("Basic Calculator");
    System.out.print("Enter number 1(enter NULL to quit): ");
    int a = sc.nextInt();
    System.out.print("\nEnter number 2: ");
    int b = sc.nextInt();
    System.out.print("Enter operation(+,-,*,/,<<,>>,&,|,~): ");
    String c = sc.next();
    switch(c) {
    case "+":
        ans = a + b;
        break;
    case "-":
        ans = a - b;
        break;
    case "*":
        ans = a * b;
        break;
    case "/":
        ans = a / b;
        break;
    case "<<":
        ans = a << b;
        break;
    case ">>":
        ans = a >> b;
        break;
    case "&":
        ans = a & b;
        break;
    case "|":
        ans = a | b;
        break;
    case "~":
        ans = ~a;
        break;
    default:
        System.out.println("Invalid Input");
    }
    System.out.println("Ans: " + ans);
    sc.close();
    }
  }
}
