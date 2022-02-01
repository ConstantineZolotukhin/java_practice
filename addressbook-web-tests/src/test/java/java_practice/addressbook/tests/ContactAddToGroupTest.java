package java_practice.addressbook.tests;

import java_practice.addressbook.model.ContactData;
import java_practice.addressbook.model.Contacts;
import java_practice.addressbook.model.GroupData;
import java_practice.addressbook.model.Groups;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactAddToGroupTest extends TestBase {

   ContactData contact;
   GroupData group;

   @BeforeMethod
   public void ensurePreconditions() {
      long now = System.currentTimeMillis();
      String contactName = String.format("FirstName%s", now);
      app.contact().create(new ContactData()
                 .withFirstName(contactName)
                 .withLastName("LastName")
                 .withAddress("33 Main St, City")
                 .withFirstEmail("address@mail.com")
                 .withHomePhone("111")
                 .withMobilePhone("222")
                 .withWorkPhone("333"));
      contact = app.db().contacts().stream().filter(c -> c.getFirstName().equals(contactName)).findFirst().get();

      app.goTo().groupsPage();
      String groupName = String.format("First Test Group%s", now);
      app.group().create(new GroupData().withName(groupName));
      group = app.db().groups().stream().filter(g -> g.getName().equals(groupName)).findFirst().get();
   }

   @Test
   public void testAddToGroup() {
      app.contact().contactAddingToGroup(contact, group);
      Assert.assertTrue(app.contact().all().contains(contact));
      verifyContactListInUI();
   }
}



