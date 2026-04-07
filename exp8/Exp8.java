import shapes.*;

public class Exp8 {
    public static void main(String[] args) {
        Square s = new Square(5);
        s.calcArea();
        s.calcPeri();
        s.display();

        System.out.println();

        Rectangle r = new Rectangle(10, 4);
        r.calcArea();
        r.calcPeri();
        r.display();
    }
}