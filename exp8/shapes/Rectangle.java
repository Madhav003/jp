package shapes;

public class Rectangle implements Polygon {
    int length;
    int breadth;
    int area;
    int peri;

    public Rectangle(int len, int bre) {
        this.length = len;
        this.breadth = bre;
    }

    public void calcArea() {
        area = length * breadth;
    }

    public void calcPeri() {
        peri = 2 * (length + breadth);
    }

    public void display() {
        System.out.println("Rectangle with length: " + length + " and breadth: " + breadth);
        System.out.println("Area: " + area);
        System.out.println("Perimeter: " + peri);
    }
}
