package java_practice.mantis.tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import javax.mail.MessagingException;
import javax.xml.rpc.ServiceException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.RemoteException;

import static org.testng.Assert.assertTrue;

public class RegistrationTests extends TestBase {

   @BeforeMethod
   public void startMailServer() throws MalformedURLException, ServiceException, RemoteException {
      skipIfNotFixed(1);
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
