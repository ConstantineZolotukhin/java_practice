package java_practice.addressbook.tests;

import java_practice.addressbook.model.GroupData;
import java_practice.addressbook.model.Groups;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class GroupDeletionTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
   app.goTo().groupPage();
   if (app.group().all().size() == 0) {
    app.group().create(new GroupData().withName("First Test Group"));
   }
 }

 @Test
  public void testGroupDeletion() throws Exception {
   Groups before = app.group().all();
   GroupData deletedGroup = before.iterator().next();
   app.group().delete(deletedGroup);
   Groups after = app.group().all();
   assertEquals(after.size(), before.size()-1);
   assertThat(after, equalTo(before.without(deletedGroup)));
  }
}