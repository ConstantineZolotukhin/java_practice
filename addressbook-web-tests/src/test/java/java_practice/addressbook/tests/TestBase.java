package java_practice.addressbook.tests;

import java_practice.addressbook.appmanager.ApplicationManager;
import java_practice.addressbook.model.ContactData;
import java_practice.addressbook.model.Contacts;
import java_practice.addressbook.model.GroupData;
import java_practice.addressbook.model.Groups;
import org.hamcrest.CoreMatchers;
import org.openqa.selenium.remote.BrowserType;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

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

   public void verifyGroupListInUI() {
      if (Boolean.getBoolean("verifyUI")) {
         Groups dbGroups = app.db().groups();
         Groups uiGroups = app.group().all();
         assertThat(uiGroups, equalTo(dbGroups.stream()
                 .map((g) -> new GroupData().withId(g.getId()).withName(g.getName()))
                 .collect(Collectors.toSet())));
      }
   }

   public void verifyContactListInUI() {
      if (Boolean.getBoolean("verifyUI")) {
         Contacts dbContacts = app.db().contacts();
         Contacts uiContacts = app.contact().all();
         assertThat(uiContacts, equalTo(dbContacts));
      }
   }
}
