package java_practice.addressbook.tests;

import java_practice.addressbook.model.GroupData;
import java_practice.addressbook.model.Groups;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class GroupCreationTests extends TestBase {

  @DataProvider
  public Iterator<Object[]> validGroups() {
    List<Object[]> list = new ArrayList<Object[]>();
    list.add(new Object[] {new GroupData().withName("First Test Group").withHeader("First Group Header").withFooter("First Group Footer")});
    list.add(new Object[] {new GroupData().withName("Second Test Group").withHeader("Second Group Header").withFooter("Second Group Footer")});
    list.add(new Object[] {new GroupData().withName("Third Test Group").withHeader("Third Group Header").withFooter("Third Group Footer")});
    return list.iterator();
  }

  @Test(dataProvider = "validGroups")
  public void testGroupCreation(GroupData group) {
    app.goTo().groupPage();
    Groups before = app.group().all();
    app.group().create(group);
    assertThat(app.group().count(), equalTo(before.size() +1));
    Groups after = app.group().all();
    assertThat(after, equalTo(
            before.withAdded(group.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
  }
}
