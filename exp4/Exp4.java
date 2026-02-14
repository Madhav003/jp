import java.util.Scanner;
class Complex{
  double rel;
  double img;
  Complex(double a, double b){
    rel = a;
    img = b;
  }
  // by constructor
  Complex add(Complex a){
    Complex temp = new Complex( this.rel + a.rel, this.img + a.img);
    return temp;
  }
  Complex sub(Complex a){
    Complex temp = new Complex( this.rel - a.rel, this.img - a.img);
    return temp;
  }
  void Display(){
    System.out.println(rel+"+i"+img);
  }
}
class Exp4{
  public static void main(String args[])
  {
    Scanner sc = new Scanner(System.in);
    System.out.println("Enter Complex num A: ");
    System.out.println("Enter a(img)");
    int i = sc.nextInt();
    System.out.println("Enter a(rel)");
    int r = sc.nextInt();
        Complex a = new Complex(r,i);
    System.out.println("Enter complex num B: ");
    System.out.println("Enter a(img)");
    i = sc.nextInt();
    System.out.println("Enter a(rel)");
    r = sc.nextInt();
    Complex b = new Complex(r,i);
    Complex c;
    c = a.add(b);
    System.out.println("addition result:");
    c.Display();
    c = a.sub(b);
    System.out.println("subtraction result:");
    c.Display();
  }
}

