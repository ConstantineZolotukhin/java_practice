package java_practice.addressbook.tests;

import com.google.gson.Gson;
import java_practice.addressbook.model.ContactData;
import java_practice.addressbook.model.Contacts;
import java_practice.addressbook.model.GroupData;
import java_practice.addressbook.model.Groups;
import org.openqa.selenium.json.TypeToken;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTests extends TestBase {

   @DataProvider
   public Iterator<Object[]> validContacts() throws IOException {
      List<Object[]> list = new ArrayList<Object[]>();
      File file = new File("src/test/resources/stru.png");
      try (BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/contacts.csv")))) {
         String line = reader.readLine();
         while (line != null) {
            String[] split = line.split(";");
            list.add(new Object[]{new ContactData()
                    .withFirstName(split[0])
                    .withLastName(split[1])
                    .withAddress(split[2])
                    .withFirstEmail(split[3])
                    .withHomePhone(split[4])
                    .withMobilePhone(split[5])
                    .withWorkPhone(split[6])
                    .withPhoto(file)});
            line = reader.readLine();
         }
         return list.iterator();
      }
   }

   @DataProvider
   public Iterator<Object[]> validContactsFromJson() throws IOException {
      List<Object[]> list = new ArrayList<Object[]>();
      File file = new File("src/test/resources/stru.png");
      try (BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/contacts.json")))) {
         String json = "";
         String line = reader.readLine();
         while (line != null) {
            json += line;
            line = reader.readLine();
         }
         Gson gson = new Gson();
         List<ContactData> contacts = gson.fromJson(json, new TypeToken<List<ContactData>>() {
         }.getType());
         return contacts.stream().map((c) -> new Object[]{c.withPhoto(file)}).collect(Collectors.toList()).iterator();
      }
   }

   @BeforeMethod
   public void ensurePreconditions() {
      app.goTo().groupsPage();
      if (app.db().groups().size() == 0) {
         app.group().create(new GroupData().withName("First Test Group"));
      }
   }

   @Test(dataProvider = "validContactsFromJson")
   public void testContactCreation(ContactData contact) throws Exception {
      Groups groups = app.db().groups();
      File photo = new File("src/test/resources/stru.png");
      ContactData newContact = new ContactData()
              .withFirstName("firstname")
              .withLastName("lastname")
              .withPhoto(photo)
              .inGroup(groups.iterator().next());
      app.goTo().homePage();
      app.contact().cleanGroupFilterDropdown();
      Contacts before = app.db().contacts();
      app.contact().create(contact);
      app.goTo().homePage();
      assertThat(app.contact().count(), equalTo(before.size() + 1));
      Contacts after = app.db().contacts();
      assertThat(after, equalTo(
              before.withAdded(contact.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt()))));
      verifyContactListInUI();
   }
}
