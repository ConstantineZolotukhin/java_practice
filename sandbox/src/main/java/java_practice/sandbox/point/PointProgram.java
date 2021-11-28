package java_practice.sandbox.point;

public class PointProgram {

   public static void main(String[] args) {

      Point p1 = new Point(1, 3);
      Point p2 = new Point(2, 4);

      System.out.println("Расстояние между точками = " + distance(p1, p2));

   }

   public static double distance(Point p1, Point p2) {

      double length = p1.x - p2.x;
      double height = p1.y - p2.y;

      return Math.sqrt(length * length + height * height);
   }

}
