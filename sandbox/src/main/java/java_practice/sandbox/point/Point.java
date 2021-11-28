package java_practice.sandbox.point;

public class Point {

   public double x;
   public double y;

   public Point(double x, double y) {
     this.x = x;
     this.y = y;
   }

   public double distance(Point p) {

      double length = p.x - x;
      double height = p.y - y;

      return Math.sqrt(length * length + height * height);
   }

}