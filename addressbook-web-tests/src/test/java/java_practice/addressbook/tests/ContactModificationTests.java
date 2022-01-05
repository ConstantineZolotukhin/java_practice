package java_practice.addressbook.tests;

import java_practice.addressbook.model.ContactData;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.Comparator;
import java.util.List;

public class ContactModificationTests extends TestBase {

   @Test
   public void testContactModification () {
      if (! app.getContactHelper().isThereAContact()) {
         app.getContactHelper().createContact(new ContactData("FirstName", "LastName", "33 Main St, City", "address@mail.com", "111222333", "First Test Group"));
      }
      List<ContactData> before = app.getContactHelper().getContactList();
      app.getContactHelper().selectContact(0);
      app.getContactHelper().initContactModification();
      ContactData contact = new ContactData(before.get(0).getId(),"FirstName UPD", "LastName UPD", "33 Main St, City UPD", "address@mail.com UPD", "111222333 UPD", null);
      app.getContactHelper().fillContactForm(contact, false);
      app.getContactHelper().submitContactModification();
      app.getNavigationHelper().returnToHomePage();
      List<ContactData> after = app.getContactHelper().getContactList();
      Assert.assertEquals(after.size(), before.size());

      before.remove(0);
      before.add(contact);
      Comparator<? super ContactData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
      before.sort(byId);
      after.sort(byId);
      Assert.assertEquals(after, before);
   }
}
