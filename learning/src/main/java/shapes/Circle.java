package shapes;

public class Circle implements Shape {
    private double r;

    public Circle(double r) {
        this.r = r;

       /* this.area = 3.14 * r * r;
        this.perimeter = 3.14 * r * 2;*/
    }


    @Override
    public double area() {
        return 3.14 * r * r;
    }

    @Override
    public double perimeter() {
        return 3.14 * r * 2;
    }
}
