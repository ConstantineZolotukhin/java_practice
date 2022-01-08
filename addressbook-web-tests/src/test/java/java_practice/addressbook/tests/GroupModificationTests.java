package java_practice.addressbook.tests;

import java_practice.addressbook.model.GroupData;
import java_practice.addressbook.model.Groups;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class GroupModificationTests extends TestBase {

   @BeforeMethod
   public void ensurePreconditions() {
      app.goTo().groupPage();
      if (app.group().all().size() == 0) {
         app.group().create(new GroupData().withName("First Test Group"));
      }
   }

   @Test
   public void testGroupModification () {
      Groups before = app.group().all();
      GroupData modifiedGroup = before.iterator().next();
      GroupData group = new GroupData()
              .withId(modifiedGroup.getId())
              .withName("First Test Group Updated")
              .withHeader("Group Header Updated")
              .withFooter("Group Footer Updated");
      app.group().modify(group);
      Groups after = app.group().all();
      assertEquals(after.size(), before.size());
      assertThat(after, equalTo(before.without(modifiedGroup).withAdded(group)));
   }
}
