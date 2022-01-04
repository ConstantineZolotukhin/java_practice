package java_practice.addressbook.tests;

import java_practice.addressbook.model.GroupData;
import org.testng.annotations.Test;

public class GroupDeletionTests extends TestBase {

 @Test
  public void testGroupDeletion() throws Exception {
   app.getNavigationHelper().gotoGroupPage();
   if (! app.getGroupHelper().isThereAGroup()) {
    app.getGroupHelper().createGroup(new GroupData("First Test Group", "Group Header", "Group Footer"));
   }
   app.getGroupHelper().selectGroup();
   app.getGroupHelper().deleteSelectedGroups();
   app.getGroupHelper().returnToGroupPage();
  }

}