public class Complex{
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
public class Exp4{
  public static void main(String args[])
  {
    Complex a = new Complex(1,2);
    Complex b = new Complex(1,2);
    Complex c;
    c = a.add(b);
    c.Display();
  }
}

