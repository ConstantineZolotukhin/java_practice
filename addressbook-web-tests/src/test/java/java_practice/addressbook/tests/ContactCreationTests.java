package java_practice.addressbook.tests;

import java_practice.addressbook.model.ContactData;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.Comparator;
import java.util.List;

public class ContactCreationTests extends TestBase {

  @Test
  public void testContactCreation() throws Exception {
    List<ContactData> before = app.getContactHelper().getContactList();
    ContactData contact = new ContactData("FirstName", "LastName", "33 Main St, City", "address@mail.com", "111222333", "First Test Group");
    app.getContactHelper().createContact(contact);
    app.getNavigationHelper().returnToHomePage();
    List<ContactData> after = app.getContactHelper().getContactList();
    Assert.assertEquals(after.size(), before.size() +1);

    before.add(contact);
    Comparator<? super ContactData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before, after);
  }
}
