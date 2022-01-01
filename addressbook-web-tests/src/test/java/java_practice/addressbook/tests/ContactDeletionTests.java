package java_practice.addressbook.tests;

import java_practice.addressbook.appmanager.NavigationHelper;
import org.testng.annotations.Test;

public class ContactDeletionTests extends TestBase {

   @Test
   public void testContactDeletion() throws Exception {
      app.getContactHelper().selectContact();
      app.getContactHelper().deleteContact();
   }
}

