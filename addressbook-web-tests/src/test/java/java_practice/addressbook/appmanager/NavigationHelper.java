package java_practice.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class NavigationHelper extends HelperBase {

   public NavigationHelper(WebDriver wd) {
      super(wd);
   }

   public void groupsPage() {
     click(By.linkText("groups"));
   }

   public void homePage() {
      click(By.linkText("home"));
   }

   public void groupPage(int id) {
//      click(By.linkText("group page \"" + name + "\""));
      click(By.xpath("//a[@href=\"./?group=" + id + "\"]"));
   }
}
