package java_practice.addressbook.tests;

import java_practice.addressbook.model.ContactData;
import org.testng.annotations.Test;

public class ContactCreationTests extends TestBase {

  @Test
  public void testContactCreation() throws Exception {
    app.initContactCreation();
    app.fillContactForm(new ContactData("FirstName", "LastName", "33 Main St, City", "address@mail.com", "111222333"));
    app.submitContactCreation();
    app.returnToHomePage();
    app.logoutAddressBook();
  }

}
