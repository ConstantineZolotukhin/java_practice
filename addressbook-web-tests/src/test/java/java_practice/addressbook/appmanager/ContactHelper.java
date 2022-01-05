package java_practice.addressbook.appmanager;

import java_practice.addressbook.model.ContactData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import java.util.ArrayList;
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

   public void deleteContact() {
      click(By.xpath("//input[@value='Delete']"));
      if (isAlertPresent())
         wd.switchTo().alert().accept();
   }

   public void submitContactCreation() {
      click(By.name("theform"));
      click(By.xpath("//div[@id='content']/form/input[21]"));
   }

   public void initContactModification() {
      click(By.xpath("//img[@alt='Edit']"));
   }

   public void submitContactModification() {
      click(By.name("update"));
   }

   public boolean isThereAContact() {
      return isElementPresent(By.name("selected[]"));
   }

   public void createContact(ContactData contact) {
      initContactCreation();
      fillContactForm(contact, true);
      submitContactCreation();
   }

   public int getContactCount() {
      return wd.findElements(By.name("selected[]")).size();
   }

   public List<ContactData> getContactList() {
      List<ContactData> contacts = new ArrayList<ContactData>();
      List<WebElement> elements = wd.findElements(By.name("entry"));
      for (WebElement element : elements) {
         String firstname = element.findElements(By.tagName("td")).get(2).getText();
         String lastname = element.findElements(By.tagName("td")).get(1).getText();
         int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
         ContactData contact = new ContactData(id, firstname, lastname);
         contacts.add(contact);
      }
      return contacts;
   }
}
