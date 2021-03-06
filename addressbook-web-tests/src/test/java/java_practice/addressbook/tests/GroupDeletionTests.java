package java_practice.addressbook.tests;

import java_practice.addressbook.model.GroupData;
import java_practice.addressbook.model.Groups;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class GroupDeletionTests extends TestBase {

   @BeforeMethod
   public void ensurePreconditions() {
      app.goTo().groupsPage();
      if (app.db().groups().size() == 0) {
         app.group().create(new GroupData().withName("First Test Group"));
      }
   }

   @Test
   public void testGroupDeletion() throws Exception {
      Groups before = app.db().groups();
      GroupData deletedGroup = before.iterator().next();
      app.group().delete(deletedGroup);
      assertThat(app.group().count(), equalTo(before.size() - 1));
      Groups after = app.db().groups();
      assertThat(after, equalTo(before.without(deletedGroup)));
      verifyGroupListInUI();
   }
}