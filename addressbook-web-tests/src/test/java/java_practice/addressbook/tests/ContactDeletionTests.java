package java_practice.addressbook.tests;

import java_practice.addressbook.model.ContactData;
import org.testng.annotations.Test;

public class ContactDeletionTests extends TestBase {

   @Test
   public void testContactDeletion() throws Exception {
      if (! app.getContactHelper().isThereAContact()) {
         app.getContactHelper().createContact(new ContactData("FirstName", "LastName", "33 Main St, City", "address@mail.com", "111222333", "First Test Group"));
      }
      app.getContactHelper().selectContact();
      app.getContactHelper().deleteContact();
   }
}

