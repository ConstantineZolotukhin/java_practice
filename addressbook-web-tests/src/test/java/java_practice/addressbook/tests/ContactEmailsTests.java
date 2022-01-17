package java_practice.addressbook.tests;

import java_practice.addressbook.model.ContactData;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactEmailsTests extends TestBase {

   @Test
   public void testContactEmails() {
      app.goTo().homePage();
      ContactData contact = app.db().contacts().iterator().next();
      ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);
      assertThat(contact.getAllEmails(), equalTo(mergeEmails(contactInfoFromEditForm)));
   }

   private String mergeEmails(ContactData contact) {
      return Arrays.asList(contact.getFirstEmail(), contact.getSecondEmail(), contact.getThirdEmail())
              .stream().filter((s) -> ! s.equals(""))
              .collect(Collectors.joining("\n"));
   }
}


