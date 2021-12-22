package java_practice.sandbox.point;
import org.testng.Assert;
import org.testng.annotations.Test;

public class PointTest {

   @Test(description = "Correct Input, Equal Result")
   public void testDistanceEqual() {

      Point p1 = new Point(1, 3);
      Point p2 = new Point(2, 4);

      Assert.assertEquals(p1.distance(p2), Math.sqrt(2));
   }

   @Test(description = "Correct Input, Not Equal Result")
   public void testDistanceNotEqual() {

      Point p1 = new Point(1, 3);
      Point p2 = new Point(2, 4);

      Assert.assertNotEquals(p1.distance(p2), Math.sqrt(3));
   }

   @Test(description = "Correct Input, No Distance")
   public void testDistanceNoDistance() {

      Point p1 = new Point(1, 3);
      Point p2 = new Point(1, 3);

      Assert.assertEquals(p1.distance(p2), 0);
   }
}
