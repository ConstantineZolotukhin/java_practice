package java_practice.addressbook.tests;

import java_practice.addressbook.model.ContactData;
import org.testng.annotations.Test;
import java.util.Arrays;
import java.util.stream.Collectors;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactAddressTests extends TestBase{

   @Test
   public void testContactAddress() {
      app.goTo().homePage();
      app.contact().cleanGroupFilterDropdown();
      ContactData contact = app.db().contacts().iterator().next();
      ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);
      assertThat(contact.getAddress(), equalTo(contactInfoFromEditForm.getAddress()));
   }
}