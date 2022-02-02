package java_practice.addressbook.tests;

import java_practice.addressbook.model.ContactData;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class AdditionalInfoContactTests extends TestBase {

   ContactData expected;
   ContactData actual;

   @BeforeMethod
   public void loadContacts() {
      app.goTo().homePage();
      app.contact().cleanGroupFilterDropdown();
      expected = app.db().contacts().iterator().next();
      actual = app.contact().all().stream().filter(c -> c.getId() == expected.getId()).findFirst().get();
   }

   @Test
   public void testAdditionalInfoContact() {
      assertThat(mergePhones(expected), equalTo(actual.getAllPhones()));
      assertThat(mergeEmails(expected), equalTo(actual.getAllEmails()));
      assertThat(expected.getAddress(), equalTo(actual.getAddress()));

   }

   private String mergePhones(ContactData contact) {
      return Arrays.asList(contact.getHomePhone(), contact.getMobilePhone(), contact.getWorkPhone(), contact.getSecondaryHomePhone())
              .stream().filter((s) -> ! s.equals(""))
              .map(AdditionalInfoContactTests::cleaned)
              .collect(Collectors.joining("\n"));

   }

   private String mergeEmails(ContactData contact) {
      return Arrays.asList(contact.getFirstEmail(), contact.getSecondEmail(), contact.getThirdEmail())
              .stream().filter((s) -> ! s.equals(""))
              .collect(Collectors.joining("\n"));
   }

   public static String cleaned(String phones) {
      return phones.replaceAll("\\s", "").replaceAll("[-()]", "");
   }

}
