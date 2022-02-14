import shapes.*;

public class Program {
    public static void main(String [] args) {
        Shape rect = new Rectangle(5, 7);
        System.out.println(String.format("rectangle area %.2f, perimeter %.2f", rect.area(), rect.perimeter()));

        Shape tri = new RightTriangle(5, 7);

        System.out.println(String.format("tri area %.2f, perimeter %.2f", tri.area(), tri.perimeter()));

        Shape sq = new Square(5);
        System.out.println(String.format("square area %.2f, perimeter %.2f", sq.area(), sq.perimeter()));

        Shape circle = new Circle(5);
        System.out.println(String.format("circle area %.2f, perimeter %.2f", circle.area(), circle.perimeter()));

        System.out.println(compareArea(circle, tri));
        System.out.println(compareArea(sq, tri));
        System.out.println(compareArea(tri, rect));
        System.out.println(compareArea(circle, sq));
    }

    public static int compareArea(Shape a, Shape b) {
        if(a.area() < b.area())
            return -1;
        if(a.area() > b.area())
            return 1;
        return 0;
    }
}
