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

   @BeforeMethod
   public void ensurePreconditions() {
      if (app.db().contacts().size() == 0) {
         app.contact().create(new ContactData()
                 .withFirstName("FirstName")
                 .withLastName("LastName")
                 .withAddress("33 Main St, City")
                 .withFirstEmail("address@mail.com")
                 .withHomePhone("111")
                 .withMobilePhone("222")
                 .withWorkPhone("333"));
      }

      if (app.db().groups().size() == 0) {
         app.goTo().groupsPage();
         app.group().create(new GroupData().withName("First Test Group"));
      }
   }

   @Test
   public void testAddToGroup() {
      ContactData addedContact = app.contact().contactAddingToGroup();
      Assert.assertTrue(app.contact().all().contains(addedContact));
      verifyContactListInUI();
   }
}