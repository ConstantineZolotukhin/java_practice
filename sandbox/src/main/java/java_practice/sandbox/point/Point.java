package java_practice.sandbox.point;

public class Point {

   public double x;
   public double y;

   public Point(double x, double y) {
     this.x = x;
     this.y = y;
   }

   public static double distance(Point p1, Point p2) {

      double length = p1.x - p2.x;
      double height = p1.y - p2.y;

      return Math.sqrt(length * length + height * height);
   }

}