package java_practice.addressbook.tests;

import java_practice.addressbook.model.GroupData;
import java_practice.addressbook.model.Groups;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class GroupModificationTests extends TestBase {

   @BeforeMethod
   public void ensurePreconditions() {
      if (app.db().groups().size() == 0) {
         app.goTo().groupsPage();
         app.group().create(new GroupData().withName("First Test Group"));
      }
   }

   @Test
   public void testGroupModification () {
      Groups before = app.db().groups();
      GroupData modifiedGroup = before.iterator().next();
      GroupData group = new GroupData()
              .withId(modifiedGroup.getId())
              .withName("First Test Group Updated")
              .withHeader("Group Header Updated")
              .withFooter("Group Footer Updated");
      app.goTo().groupsPage();
      app.group().modify(group);
      assertThat(app.group().count(), equalTo(before.size()));
      Groups after = app.db().groups();
      assertThat(after, equalTo(before.without(modifiedGroup).withAdded(group)));
      verifyGroupListInUI();
   }
}
