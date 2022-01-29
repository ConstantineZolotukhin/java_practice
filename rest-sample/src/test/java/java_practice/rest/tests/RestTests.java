package java_practice.rest.tests;

import java_practice.rest.model.Issue;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Set;

import static org.testng.Assert.assertEquals;

public class RestTests extends TestBase {

   @BeforeMethod
   public void checkExistingIssues() throws IOException {
      skipIfNotFixed(1866);
   }

   @Test
   public void testCreateIssue() throws IOException {
      Set<Issue> oldIssues = app.rest().getIssues();
      Issue newIssue = new Issue().withSubject("Test issue").withDescription("New test issue");
      int issueId = app.rest().createIssue(newIssue);
      Set<Issue> newIssues = app.rest().getIssues();
      oldIssues.add(newIssue.withId(issueId));
      assertEquals(newIssues, oldIssues);
   }
}
