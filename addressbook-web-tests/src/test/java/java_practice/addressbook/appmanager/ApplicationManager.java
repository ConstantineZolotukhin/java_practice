package java_practice.addressbook.appmanager;

import java_practice.addressbook.model.ContactData;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class ApplicationManager {

   protected WebDriver wd;

   private SessionHelper sessionHelper;
   private NavigationHelper navigationHelper;
   private GroupHelper groupHelper;

   public void init() {
      wd = new ChromeDriver();
      wd.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
      wd.get("http://localhost/addressbook/");
      groupHelper = new GroupHelper(wd);
      navigationHelper = new NavigationHelper(wd);
      sessionHelper = new SessionHelper(wd);
      sessionHelper.login("admin", "secret");
   }

   public void logoutAddressBook() {
     wd.findElement(By.linkText("Logout")).click();
   }

   public void stop() {
      wd.quit();
   }

   private boolean isElementPresent(By by) {
     try {
       wd.findElement(by);
       return true;
     } catch (NoSuchElementException e) {
       return false;
     }
   }

   private boolean isAlertPresent() {
     try {
       wd.switchTo().alert();
       return true;
     } catch (NoAlertPresentException e) {
       return false;
     }
   }

   public void submitContactCreation() {
     wd.findElement(By.name("theform")).click();
     wd.findElement(By.xpath("//div[@id='content']/form/input[21]")).click();
   }

   public void returnToHomePage() {
     wd.findElement(By.linkText("home page")).click();
   }

   public void fillContactForm(ContactData contactData) {
     wd.findElement(By.name("firstname")).click();
     wd.findElement(By.name("firstname")).clear();
     wd.findElement(By.name("firstname")).sendKeys(contactData.getFirstName());
     wd.findElement(By.name("lastname")).click();
     wd.findElement(By.name("lastname")).clear();
     wd.findElement(By.name("lastname")).sendKeys(contactData.getLastName());
     wd.findElement(By.name("theform")).click();
     wd.findElement(By.name("address")).click();
     wd.findElement(By.name("address")).clear();
     wd.findElement(By.name("address")).sendKeys(contactData.getAddress());
     wd.findElement(By.name("email")).click();
     wd.findElement(By.name("email")).clear();
     wd.findElement(By.name("email")).sendKeys(contactData.getMail());
     wd.findElement(By.xpath("//body")).click();
     wd.findElement(By.name("home")).click();
     wd.findElement(By.name("mobile")).click();
     wd.findElement(By.name("home")).click();
     wd.findElement(By.name("home")).clear();
     wd.findElement(By.name("home")).sendKeys(contactData.getPhone());
   }

   public void initContactCreation() {
     wd.findElement(By.linkText("add new")).click();
   }

   public GroupHelper getGroupHelper() {
      return groupHelper;
   }

   public NavigationHelper getNavigationHelper() {
      return navigationHelper;
   }
}
