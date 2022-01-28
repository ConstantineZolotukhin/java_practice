package java_practice.mantis.tests;

import java_practice.mantis.appmanager.HttpSession;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import javax.xml.rpc.ServiceException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.RemoteException;

import static org.testng.Assert.assertTrue;

public class LoginTests extends TestBase {

   @BeforeMethod
   public void startMailServer() throws MalformedURLException, ServiceException, RemoteException {
      skipIfNotFixed(1);
   }

   @Test
   public void testLogin() throws IOException {
      HttpSession session = app.newSession();
      assertTrue(session.login("administrator", "root"));
      assertTrue(session.isLoggedInAs("administrator"));
   }
}
