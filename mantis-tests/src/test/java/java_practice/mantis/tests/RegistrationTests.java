package java_practice.mantis.tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import javax.mail.MessagingException;
import java.io.IOException;

import static org.testng.Assert.assertTrue;

public class RegistrationTests extends TestBase {

   @BeforeMethod
   public void startMailServer() {
      app.mail().start();
   }

   @Test
   public void testRegistration() throws MessagingException, IOException {
      long now = System.currentTimeMillis();
      String user = String.format("user%s", now);
      String password = "password";
      String email = String.format("user%s@localhost.localdomain", now);
      app.mantis().userRegistration(user, password, email);
      assertTrue(app.newSession().login(user, password));
   }

   @AfterMethod(alwaysRun = true)
   public void stopMailServer() {
      app.mail().stop();
   }
}
