package java_practice.addressbook.tests;

import java_practice.addressbook.model.ContactData;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.List;

public class ContactDeletionTests extends TestBase {

   @Test
   public void testContactDeletion() throws Exception {
      if (! app.getContactHelper().isThereAContact()) {
         app.getContactHelper().createContact(new ContactData("FirstName", "LastName", "33 Main St, City", "address@mail.com", "111222333", "First Test Group"));
      }
      List<ContactData> before = app.getContactHelper().getContactList();
      app.getContactHelper().selectContact(before.size()-1);
      app.getContactHelper().deleteContact();
      app.getNavigationHelper().returnToHomePage();
      List<ContactData> after = app.getContactHelper().getContactList();
      Assert.assertEquals(after.size(), before.size()-1);

      before.remove(before.size()-1);
      Assert.assertEquals(before, after);
   }
}

