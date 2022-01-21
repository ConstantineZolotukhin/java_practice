package java_practice.addressbook.tests;

import java_practice.addressbook.model.ContactData;
import java_practice.addressbook.model.Contacts;
import java_practice.addressbook.model.GroupData;
import java_practice.addressbook.model.Groups;
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
   }

   @Test
   public void testAddToGroup() {
      app.goTo().homePage();
      Contacts contactsList = app.db().contacts();
      ContactData addedContact = contactsList.iterator().next();
      Groups groupsList = app.db().groups();
      GroupData group = groupsList.iterator().next();
      app.contact().addToGroup(addedContact, group);
      app.goTo().groupPage(group.getId());
      assertThat(app.contact().count(), equalTo(group.getContacts().size() + 1));

//      Contacts after = app.db().contacts();
//      assertThat(after, equalTo(before.without(modifiedContact).withAdded(contact)));
      verifyContactListInUI();
   }

}
