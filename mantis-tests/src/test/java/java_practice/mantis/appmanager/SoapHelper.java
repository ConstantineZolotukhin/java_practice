package java_practice.mantis.appmanager;

import biz.futureware.mantis.rpc.soap.client.IssueData;
import biz.futureware.mantis.rpc.soap.client.MantisConnectPortType;
import biz.futureware.mantis.rpc.soap.client.ObjectRef;
import biz.futureware.mantis.rpc.soap.client.ProjectData;
import java_practice.mantis.model.Issue;
import java_practice.mantis.model.Project;

import javax.xml.rpc.ServiceException;
import java.math.BigInteger;
import java.net.MalformedURLException;
import java.rmi.RemoteException;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public class SoapHelper {

   private ApplicationManager app;

   public SoapHelper(ApplicationManager app) {
      this.app = app;
   }

   public Set<Project> getProjects() throws MalformedURLException, RemoteException, ServiceException {
      MantisConnectPortType mc = app.mantis().getMantisConnect();
      ProjectData[] projects = mc.mc_projects_get_user_accessible("administrator", "root");
      return Arrays.asList(projects).stream()
              .map((p) -> new Project().withId(p.getId().intValue()).withName(p.getName()))
              .collect(Collectors.toSet());
   }

   public Issue addIssue(Issue issue) throws MalformedURLException, RemoteException, ServiceException {
      MantisConnectPortType mc = app.mantis().getMantisConnect();
      String[] categories = mc.mc_project_get_categories("administrator", "root", BigInteger.valueOf(issue.getProject().getId()));
      IssueData issueData = new IssueData();
      issueData.setSummary(issue.getSummary());
      issueData.setDescription(issue.getDescription());
      issueData.setProject(new ObjectRef(BigInteger.valueOf(issue.getProject().getId()), (issue.getProject().getName())));
      issueData.setCategory(categories[0]);
      BigInteger issueId = mc.mc_issue_add("administrator", "root", issueData);
      IssueData createdIssueData = mc.mc_issue_get("administrator", "root", issueId);
      return new Issue().withId(createdIssueData.getId().intValue())
              .withSummary(createdIssueData.getSummary())
              .withDescription(createdIssueData.getDescription())
              .withProject(new Project().withId(createdIssueData.getProject().getId().intValue())
                                        .withName(createdIssueData.getProject().getName()));
   }

   public Issue getIssue(int id) throws MalformedURLException, ServiceException, RemoteException {
      MantisConnectPortType mc = app.mantis().getMantisConnect();
      IssueData issueData = mc.mc_issue_get("administrator", "root", BigInteger.valueOf(id));
      return new Issue().withId(issueData.getId().intValue())
              .withSummary(issueData.getSummary())
              .withDescription(issueData.getDescription())
              .withStatus(issueData.getStatus().getName())
              .withProject(new Project().withId(issueData.getProject().getId().intValue())
                      .withName(issueData.getProject().getName()));
   }
}
