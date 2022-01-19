package java_practice.addressbook.appmanager;

import java_practice.addressbook.model.ContactData;
import java_practice.addressbook.model.Contacts;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import java.util.List;

public class ContactHelper extends HelperBase {

   public ContactHelper(WebDriver wd) {
      super(wd);
   }

   public void initContactCreation() {
      click(By.linkText("add new"));
   }

   public void fillContactForm(ContactData contactData, boolean creation) {
      type(By.name("firstname"), contactData.getFirstName());
      type(By.name("lastname"), contactData.getLastName());
      type(By.name("address"), contactData.getAddress());
      type(By.name("email"), contactData.getFirstEmail());
      type(By.name("home"), contactData.getHomePhone());
      type(By.name("mobile"), contactData.getMobilePhone());
      type(By.name("work"), contactData.getWorkPhone());
      attach(By.name("photo"), contactData.getPhoto());

      if (creation) {
         if (contactData.getGroups().size() > 0) {
            Assert.assertTrue(contactData.getGroups().size() == 1);
            new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData
                    .getGroups()
                    .iterator()
                    .next()
                    .getName());
      }
      } else {
         Assert.assertFalse(isElementPresent(By.name("new_group")));
      }
   }

   public void selectContact(int index) {
      wd.findElements(By.name("selected[]")).get(index).click();
   }

   public void selectContactById(int id) {
      wd.findElement(By.cssSelector("input[value='" + id + "']")).click();
   }

   public void deleteContact() {
      click(By.xpath("//input[@value='Delete']"));
      if (isAlertPresent())
         wd.switchTo().alert().accept();
   }

   public void submitContactCreation() {
      click(By.name("theform"));
      click(By.xpath("//div[@id='content']/form/input[21]"));
   }

   public void homePage() {
      click(By.linkText("home"));
   }

   public void initContactModificationById(int id) {
      click(By.xpath("//a[@href=\"edit.php?id=" + id + "\"]"));
   }

   public void submitContactModification() {
      click(By.name("update"));
   }

   public boolean isThereAContact() {
      return isElementPresent(By.name("selected[]"));
   }

   public void create(ContactData contact) {
      initContactCreation();
      fillContactForm(contact, true);
      submitContactCreation();
      contactCache = null;
   }

   public void modify(ContactData contact) {
      initContactModificationById(contact.getId());
      fillContactForm(contact, false);
      submitContactModification();
      contactCache = null;
      homePage();
   }

   public void delete(ContactData contact) {
      selectContactById(contact.getId());
      deleteContact();
      contactCache = null;
      homePage();
   }

   public int count() {
      return wd.findElements(By.name("selected[]")).size();
   }

   private Contacts contactCache = null;

   public Contacts all() {
      if (contactCache != null) {
         return new Contacts(contactCache);
      }
      contactCache = new Contacts();
      List<WebElement> rows = wd.findElements(By.name("entry"));
      for (WebElement row : rows) {
         List<WebElement> cells = row.findElements(By.tagName("td"));
         String lastname = cells.get(1).getText();
         String firstname = cells.get(2).getText();
         String address = cells.get(3).getText();
         String emails = cells.get(4).getText();
         String allPhones = cells.get(5).getText();
         int id = Integer.parseInt(row.findElement(By.tagName("input")).getAttribute("value"));
         contactCache.add(new ContactData()
                 .withId(id)
                 .withFirstName(firstname)
                 .withLastName(lastname)
                 .withAddress(address)
                 .withAllEmails(emails)
                 .withAllPhones(allPhones));

      }
      return new Contacts(contactCache);
   }

   public ContactData infoFromEditForm(ContactData contact) {
      initContactModificationById(contact.getId());
      String firstname = wd.findElement(By.name("firstname")).getAttribute("value");
      String lastname = wd.findElement(By.name("lastname")).getAttribute("value");
      String address = wd.findElement(By.name("address")).getAttribute("value");
      String firstEmail = wd.findElement(By.name("email")).getAttribute("value");
      String secondEmail = wd.findElement(By.name("email2")).getAttribute("value");
      String thirdEmail = wd.findElement(By.name("email3")).getAttribute("value");
      String home = wd.findElement(By.name("home")).getAttribute("value");
      String mobile = wd.findElement(By.name("mobile")).getAttribute("value");
      String work = wd.findElement(By.name("work")).getAttribute("value");
      wd.navigate().back();
      return new ContactData().withId(contact.getId())
              .withFirstName(firstname)
              .withLastName(lastname)
              .withAddress(address)
              .withFirstEmail(firstEmail)
              .withSecondEmail(secondEmail)
              .withThirdEmail(thirdEmail)
              .withHomePhone(home)
              .withMobilePhone(mobile)
              .withWorkPhone(work);
   }
}
