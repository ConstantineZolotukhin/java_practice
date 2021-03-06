package java_practice.addressbook.tests;

import java_practice.addressbook.model.ContactData;
import java_practice.addressbook.model.Contacts;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactModificationTests extends TestBase {

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
   public void testContactModification() {
      app.goTo().homePage();
      app.contact().cleanGroupFilterDropdown();
      Contacts before = app.db().contacts();
      ContactData modifiedContact = before.iterator().next();
      ContactData contact = new ContactData()
              .withId(modifiedContact.getId())
              .withFirstName("FirstName UPD")
              .withLastName("LastName UPD")
              .withAddress("33 Main St, City UPD")
              .withFirstEmail("address@mail.com UPD")
              .withHomePhone("111 UPD")
              .withMobilePhone("222 UPD")
              .withWorkPhone("333 UPD");
      app.contact().modify(contact);
      assertThat(app.contact().count(), equalTo(before.size()));
      Contacts after = app.db().contacts();
      assertThat(after, equalTo(before.without(modifiedContact).withAdded(contact)));
      verifyContactListInUI();
   }
}
