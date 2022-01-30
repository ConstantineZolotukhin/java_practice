package java_practice.addressbook.tests;

import java_practice.addressbook.model.ContactData;
import org.testng.annotations.Test;
import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactPhoneTests extends TestBase {

   @Test
   public void testContactPhones() {
      app.goTo().homePage();
      app.contact().cleanGroupFilterDropdown();
      ContactData contact = app.db().contacts().iterator().next();
      ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);
      assertThat(contact.getHomePhone(), equalTo(contactInfoFromEditForm.getHomePhone()));
      assertThat(contact.getMobilePhone(), equalTo(contactInfoFromEditForm.getMobilePhone()));
      assertThat(contact.getWorkPhone(), equalTo(contactInfoFromEditForm.getWorkPhone()));
   }

   private String mergePhones(ContactData contact) {
      return Arrays.asList(contact.getHomePhone(), contact.getMobilePhone(), contact.getWorkPhone())
              .stream().filter((s) -> ! s.equals(""))
              .map(ContactPhoneTests::cleaned)
              .collect(Collectors.joining("\n"));

   }
   public static String cleaned(String phones) {
      return phones.replaceAll("\\s", "").replaceAll("[-()]", "");
   }
}
