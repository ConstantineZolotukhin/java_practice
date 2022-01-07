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
         app.contact().create(new ContactData()
                 .withFirstName("FirstName")
                 .withLastName("LastName")
                 .withAddress("33 Main St, City")
                 .withMail("address@mail.com")
                 .withPhone("111222333")
                 .withGroup("First Test Group"));
      }
   }

   @Test
   public void testContactModification () {
      List<ContactData> before = app.contact().list();
      int index = 0;
      ContactData contact = new ContactData()
              .withId(before.get(index).getId())
              .withFirstName("FirstName UPD")
              .withLastName("LastName UPD")
              .withAddress("33 Main St, City UPD")
              .withMail("address@mail.com UPD")
              .withPhone("111222333 UPD");
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
