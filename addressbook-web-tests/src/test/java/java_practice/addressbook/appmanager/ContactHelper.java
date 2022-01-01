package java_practice.addressbook.appmanager;

import java_practice.addressbook.model.ContactData;
import java_practice.addressbook.model.GroupData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ContactHelper extends HelperBase {

   public ContactHelper(WebDriver wd) {
      super(wd);
   }

   public void initContactCreation() {
      click(By.linkText("add new"));
   }

   public void fillContactForm(ContactData contactData) {
      type(By.name("firstname"), contactData.getFirstName());
      type(By.name("lastname"), contactData.getLastName());
      type(By.name("address"), contactData.getAddress());
      type(By.name("email"), contactData.getMail());
      type(By.name("home"), contactData.getPhone());
   }

   public void selectContact() {
      click(By.name("selected[]"));
   }

   public void deleteContact() {
      click(By.xpath("//input[@value='Delete']"));
      if(isAlertPresent())
         wd.switchTo().alert().accept();
   }

   public void submitContactCreation() {
      click(By.name("theform"));
      click(By.xpath("//div[@id='content']/form/input[21]"));
   }
}


