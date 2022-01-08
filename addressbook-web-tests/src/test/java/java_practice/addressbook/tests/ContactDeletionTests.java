package java_practice.addressbook.tests;

import java_practice.addressbook.model.ContactData;
import java_practice.addressbook.model.Contacts;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class ContactDeletionTests extends TestBase {

   @BeforeMethod
   public void ensurePreconditions() {
      if (app.contact().all().size() == 0) {
         app.contact().create(new ContactData().withFirstName("FirstName").withGroup("First Test Group"));
      }
   }

   @Test
   public void testContactDeletion() throws Exception {
      app.goTo().homePage();
      Contacts before = app.contact().all();
      ContactData deletedContact = before.iterator().next();
      app.contact().delete(deletedContact);
      Contacts after = app.contact().all();
      assertEquals(after.size(), before.size()-1);
      assertThat(after, equalTo(before.without(deletedContact)));
   }
}

