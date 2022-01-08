package java_practice.addressbook.appmanager;

import java_practice.addressbook.model.ContactData;
import java_practice.addressbook.model.Contacts;
import java_practice.addressbook.model.Groups;
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
      type(By.name("email"), contactData.getMail());
      type(By.name("home"), contactData.getPhone());

      if (creation) {
         new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroup());
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

   public void initContactModification(int id) {
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
      initContactModification(contact.getId());
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
      List<WebElement> elements = wd.findElements(By.name("entry"));
      for (WebElement element : elements) {
         String firstname = element.findElements(By.tagName("td")).get(2).getText();
         String lastname = element.findElements(By.tagName("td")).get(1).getText();
         int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
         contactCache.add(new ContactData().withId(id).withFirstName(firstname).withLastName(lastname));
      }
      return new Contacts(contactCache);
   }
}
