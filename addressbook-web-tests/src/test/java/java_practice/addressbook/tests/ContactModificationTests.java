package java_practice.addressbook.tests;

import java_practice.addressbook.model.ContactData;
import org.testng.annotations.Test;

public class ContactModificationTests extends TestBase {

   @Test
   public void testContactModification () {
      if (! app.getContactHelper().isThereAContact()) {
         app.getContactHelper().createContact(new ContactData("FirstName", "LastName", "33 Main St, City", "address@mail.com", "111222333", "First Test Group"));
      }
      app.getContactHelper().selectContact();
      app.getContactHelper().initContactModification();
      app.getContactHelper().fillContactForm(new ContactData("FirstName UPD", "LastName UPD", "33 Main St, City UPD", "address@mail.com UPD", "111222333 UPD", null), false);
      app.getContactHelper().submitContactModification();
   }
}