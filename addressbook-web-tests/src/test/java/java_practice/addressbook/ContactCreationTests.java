package java_practice.addressbook;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

public class ContactCreationTests extends TestBase {

  @Test
  public void testContactCreation() throws Exception {
    initContactCreation();
    fillContactForm(new ContactData("FirstName", "LastName", "33 Main St, City", "address@mail.com", "111222333"));
    submitContactCreation();
    returnToHomePage();
    logoutAddressBook();
  }

}
