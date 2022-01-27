package java_practice.mantis.tests;

import java_practice.mantis.model.Issue;
import java_practice.mantis.model.Project;
import org.testng.Assert;
import org.testng.annotations.Test;

import javax.xml.rpc.ServiceException;
import java.net.MalformedURLException;
import java.rmi.RemoteException;
import java.util.Set;

import static org.testng.Assert.assertEquals;

public class SoapTests extends TestBase {

   @Test
   public void testGetProject() throws MalformedURLException, RemoteException, ServiceException {
      Set<Project> projects = app.soap().getProjects();
      System.out.println(projects.size());
      for (Project project : projects) {
         System.out.println(project.getName());
      }
   }

   @Test
   public void testCreateIssue() throws MalformedURLException, RemoteException, ServiceException {
      Set<Project> projects = app.soap().getProjects();
      Issue issue = new Issue().withSummary("Test issue")
              .withDescription("Test issue description")
              .withProject(projects.iterator().next());
      Issue created = app.soap().addIssue(issue);
      assertEquals(issue.getSummary(), created.getSummary());
   }
}
