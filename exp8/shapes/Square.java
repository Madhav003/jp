package shapes;

public class Square implements Polygon {
    float side;
    float area;
    float peri;

    public Square(float s) {
        this.side = s;
    }

    public void calcArea() {
        area = side * side;
    }

    public void calcPeri() {
        peri = 4 * side;
    }

    public void display() {
        System.out.println("Square with side: " + side);
        System.out.println("Area: " + area);
        System.out.println("Perimeter: " + peri);
    }
}
