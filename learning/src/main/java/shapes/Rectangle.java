package shapes;

public class Rectangle implements Shape {

    protected double a;
    protected double b;

    public Rectangle(double a, double b) {
        this.a = a;
        this.b = b;

     /*   this.area = a * b;
        this.perimeter = (a + b) * 2;*/
    }

    @Override
    public double area() {
        return a * b;
    }

    @Override
    public double perimeter() {
        return (a + b) * 2;
    }
}
