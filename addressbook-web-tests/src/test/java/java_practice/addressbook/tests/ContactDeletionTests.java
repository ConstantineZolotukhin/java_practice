package java_practice.addressbook.tests;

import java_practice.addressbook.model.ContactData;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.util.List;

public class ContactDeletionTests extends TestBase {

   @BeforeMethod
   public void ensurePreconditions() {
      if (app.contact().list().size() == 0) {
         app.contact().create(new ContactData().withFirstName("FirstName").withGroup("First Test Group"));
      }
   }

   @Test
   public void testContactDeletion() throws Exception {
      List<ContactData> before = app.contact().list();
      int index = before.size()-1;
      app.contact().delete(index);
      List<ContactData> after = app.contact().list();
      Assert.assertEquals(after.size(), before.size()-1);

      before.remove(index);
      Assert.assertEquals(before, after);
   }
}

