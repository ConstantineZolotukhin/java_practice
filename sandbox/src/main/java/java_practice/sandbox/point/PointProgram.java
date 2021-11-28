package java_practice.sandbox.point;

public class PointProgram {

   public static void main(String[] args) {

      Point p1 = new Point(1, 3);
      Point p2 = new Point(2, 4);

      System.out.println("Расстояние между точками = " + p1.distance(p2));

   }

}