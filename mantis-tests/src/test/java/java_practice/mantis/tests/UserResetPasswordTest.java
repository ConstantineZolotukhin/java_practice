package java_practice.mantis.tests;

import java_practice.mantis.appmanager.HttpSession;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import javax.mail.MessagingException;
import javax.xml.rpc.ServiceException;
import java.io.IOException;

import static org.testng.Assert.assertTrue;

public class UserResetPasswordTest extends TestBase {
   String user;
   String password;
   String newPassword;
   String email;

   @BeforeMethod
   public void precondition() throws MessagingException, IOException, ServiceException {
      skipIfNotFixed(1);
      app.mail().start();
      long now = System.currentTimeMillis();
      user = String.format("user%s", now);
      password = "password";
      email = String.format("user%s@localhost.localdomain", now);
      app.mantis().userRegistration(user, password, email);
   }

   @Test
   public void resetPassword() throws MessagingException, IOException {
      app.mantis().mantisLogin("administrator", "root");
      newPassword = "newPassword";
      app.mantis().userPasswordReset(user, email, newPassword);
      HttpSession session = app.newSession();
      assertTrue(session.login(user, newPassword));
      assertTrue(session.isLoggedInAs(user));
   }

   @AfterMethod(alwaysRun = true)
   public void stopMailServer() {
      app.mail().stop();
   }
}
