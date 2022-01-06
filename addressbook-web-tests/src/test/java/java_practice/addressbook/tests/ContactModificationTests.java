package java_practice.addressbook.tests;

import java_practice.addressbook.model.ContactData;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.util.Comparator;
import java.util.List;

public class ContactModificationTests extends TestBase {

   @BeforeMethod
   public void ensurePreconditions() {
      if (app.contact().list().size() == 0) {
         app.contact().create(new ContactData("FirstName", "LastName", "33 Main St, City", "address@mail.com", "111222333", "First Test Group"));
      }
   }

   @Test
   public void testContactModification () {
      List<ContactData> before = app.contact().list();
      int index = 0;
      ContactData contact = new ContactData(before.get(index).getId(),"FirstName UPD", "LastName UPD", "33 Main St, City UPD", "address@mail.com UPD", "111222333 UPD", null);
      app.contact().modify(contact);
      List<ContactData> after = app.contact().list();
      Assert.assertEquals(after.size(), before.size());

      before.remove(index);
      before.add(contact);
      Comparator<? super ContactData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
      before.sort(byId);
      after.sort(byId);
      Assert.assertEquals(after, before);
   }
}
