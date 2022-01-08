package java_practice.addressbook.tests;

import java_practice.addressbook.model.GroupData;
import java_practice.addressbook.model.Groups;
import org.testng.annotations.Test;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class GroupCreationTests extends TestBase {

  @Test
  public void testGroupCreation() throws Exception {
    app.goTo().groupPage();
    Groups before = app.group().all();
    GroupData group = new GroupData()
            .withName("First Test Group")
            .withFooter("Group Header")
            .withHeader("Group Footer");
    app.group().create(group);
    assertThat(app.group().count(), equalTo(before.size() +1));
    Groups after = app.group().all();
    assertThat(after, equalTo(
            before.withAdded(group.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
  }
}
