package java_practice.addressbook.tests;

import java_practice.addressbook.model.ContactData;
import java_practice.addressbook.model.Contacts;
import java_practice.addressbook.model.GroupData;
import java_practice.addressbook.model.Groups;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.security.acl.Group;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactDeleteFromGroupTest extends TestBase {

   ContactData addedContact;

   @BeforeMethod
   public void ensurePreconditions() {
      if (app.db().contacts().size() == 0) {
         app.contact().create(new ContactData()
                 .withFirstName("FirstName")
                 .withLastName("LastName")
                 .withAddress("33 Main St, City")
                 .withFirstEmail("address@mail.com")
                 .withHomePhone("111")
                 .withMobilePhone("222")
                 .withWorkPhone("333"));
      }
      if (app.db().groups().size() == 0) {
         app.goTo().groupsPage();
         app.group().create(new GroupData().withName("First Test Group"));
      }
      Contacts contactsList = app.db().contacts();
      addedContact = contactsList.iterator().next();
      Groups groupsList = app.db().groups();
      GroupData group = groupsList.iterator().next();
      app.contact().contactAddingToGroup(addedContact, group);
   }

   @Test
   public void testDeleteFromGroup() {
      Groups groups = app.db().groups();
      GroupData fromGroup = null;
      for(GroupData group : groups) {
         if(group.getContacts().contains(addedContact)) {
            fromGroup = group;
         }
      }
      app.contact().selectGroupFilterDropdown(fromGroup.getId());
      app.contact().selectContactById(addedContact.getId());
      app.contact().submitContactDeleteFromGroup();
      int fromGroupId = fromGroup.getId();
      fromGroup = app.db().groups().stream().filter(g -> g.getId() == fromGroupId).findFirst().get();
      Assert.assertFalse(fromGroup.getContacts().contains(addedContact));
      verifyContactListInUI();
   }
}
