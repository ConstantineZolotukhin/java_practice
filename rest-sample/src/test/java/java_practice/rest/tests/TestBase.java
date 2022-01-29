package java_practice.rest.tests;

import java_practice.rest.appmanager.ApplicationManager;
import java_practice.rest.model.Issue;
import org.testng.SkipException;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.RemoteException;

public class TestBase {

   protected static final ApplicationManager app = new ApplicationManager();

   @BeforeSuite(alwaysRun = true)
   public void setUp() throws Exception {
      app.init();
   }

   public boolean isIssueOpen(int issueId) throws IOException {
      Issue issue = app.rest().getIssue(issueId);
      if (issue == null) {
         return false;
      }
      return !issue.getStatus().equals("Closed");
   }

   public void skipIfNotFixed(int issueId) throws IOException {
      if (isIssueOpen(issueId)) {
         throw new SkipException("Ignored because of issue " + issueId);
      }
   }
}
