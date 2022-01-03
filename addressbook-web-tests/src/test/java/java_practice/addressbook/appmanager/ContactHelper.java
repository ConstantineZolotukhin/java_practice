package java_practice.addressbook.appmanager;

import java_practice.addressbook.model.ContactData;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

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

   public void selectContact() {
      click(By.name("selected[]"));
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
}
