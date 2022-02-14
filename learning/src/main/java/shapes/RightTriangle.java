package shapes;

public class RightTriangle implements Shape {
    private double a;
    private double b;

    public RightTriangle(double a, double b) {
        this.a = a;
        this.b = b;

        /*this.area = a * b / 2;

        this.perimeter = a + b + getHypo();*/
    }

    public double getHypo() {
        return Math.sqrt(a*a + b*b);
    }

    @Override
    public double area() {
        return a * b / 2;
    }

    @Override
    public double perimeter() {
        return a + b + getHypo();
    }
}
