package java_practice.addressbook;

import org.testng.annotations.Test;

public class GroupCreationTests extends TestBase {

  @Test
  public void testGroupCreation() throws Exception {
    gotoGroupPage();
    initGroupCreation();
    fillGroupForm(new GroupData("First Test Group", "Group Header", "Group Footer"));
    submitGroupCreation();
    returnToGroupPage();
    logoutAddressBook();
  }
}
