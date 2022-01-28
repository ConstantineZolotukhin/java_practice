package java_practice.mantis.tests;

import java_practice.mantis.appmanager.ApplicationManager;
import java_practice.mantis.model.Issue;
import org.openqa.selenium.remote.BrowserType;
import org.testng.SkipException;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import javax.xml.rpc.ServiceException;
import java.net.MalformedURLException;
import java.rmi.RemoteException;

public class TestBase {

   protected static final ApplicationManager app
           = new ApplicationManager(System.getProperty("browser", BrowserType.CHROME));

   @BeforeSuite(alwaysRun = true)
   public void setUp() throws Exception {
      app.init();
   }

   @AfterSuite(alwaysRun = true)
   public void tearDown() throws Exception {
      app.stop();
   }

   public boolean isIssueOpen(int issueId) throws MalformedURLException, ServiceException, RemoteException {
      Issue issue = app.soap().getIssue(issueId);
      return !issue.getStatus().equals("closed");
   }

   public void skipIfNotFixed(int issueId) throws MalformedURLException, ServiceException, RemoteException {
      if (isIssueOpen(issueId)) {
         throw new SkipException("Ignored because of issue " + issueId);
      }
   }
}
