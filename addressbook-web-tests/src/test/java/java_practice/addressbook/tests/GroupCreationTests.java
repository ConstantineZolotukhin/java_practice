package java_practice.addressbook.tests;

import java_practice.addressbook.model.GroupData;
import org.testng.annotations.Test;

public class GroupCreationTests extends TestBase {

  @Test
  public void testGroupCreation() throws Exception {
    app.gotoGroupPage();
    app.initGroupCreation();
    app.fillGroupForm(new GroupData("First Test Group", "Group Header", "Group Footer"));
    app.submitGroupCreation();
    app.returnToGroupPage();
    app.logoutAddressBook();
  }
}
