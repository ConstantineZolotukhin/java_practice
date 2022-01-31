package java_practice.addressbook.tests;

import java_practice.addressbook.model.ContactData;
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
      actual = app.contact().infoFromEditForm(expected);
   }

   @Test
   public void testContactPhones() {
      assertThat(expected.getHomePhone(), equalTo(actual.getHomePhone()));
      assertThat(expected.getMobilePhone(), equalTo(actual.getMobilePhone()));
      assertThat(expected.getWorkPhone(), equalTo(actual.getWorkPhone()));
      assertThat(expected.getSecondaryHomePhone(), equalTo(actual.getSecondaryHomePhone()));
   }

   @Test
   public void testContactEmails() {
      assertThat(expected.getFirstEmail(), equalTo(actual.getFirstEmail()));
      assertThat(expected.getSecondEmail(), equalTo(actual.getSecondEmail()));
      assertThat(expected.getThirdEmail(), equalTo(actual.getThirdEmail()));
   }

   @Test
   public void testContactAddress() {
      assertThat(expected.getAddress(), equalTo(actual.getAddress()));
   }

   private String mergePhones(ContactData contact) {
      return Arrays.asList(contact.getHomePhone(), contact.getMobilePhone(), contact.getWorkPhone())
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
