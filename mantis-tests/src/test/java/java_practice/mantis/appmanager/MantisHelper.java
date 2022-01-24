package java_practice.mantis.appmanager;

import java_practice.mantis.model.MailMessage;
import org.openqa.selenium.By;
import ru.lanwen.verbalregex.VerbalExpression;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;

import static org.testng.Assert.assertTrue;

public class MantisHelper extends HelperBase {

   public MantisHelper(ApplicationManager app) {
      super(app);
   }

   public void mantisLogin(String username, String password) {
      wd.get(app.getProperty("web.baseUrl") + "/login_page.php");
      type(By.name("username"), username);
      click(By.cssSelector("input[type='submit']"));
      type(By.name("password"), password);
      click(By.cssSelector("input[type='submit']"));
   }

   public void start(String username, String email) {
      wd.get(app.getProperty("web.baseUrl") + "/signup_page.php");
      type(By.name("username"), username);
      type(By.name("email"), email);
      click(By.cssSelector("input[value='Signup']"));
   }

   public void finish(String confirmationLink, String password) {
      wd.get(confirmationLink);
      type(By.id("password"), password);
      type(By.id("password-confirm"), password);
      click(By.cssSelector("button[type='submit']"));
   }

   public void userRegistration(String user, String password, String email) throws MessagingException, IOException {
      start(user, email);
      List<MailMessage> mailMessages = app.mail().waitForMail(2, 10000);
      String confirmationLink = findLink(mailMessages, email, "Thank you for registering");
      finish(confirmationLink, password);
   }

   private String findLink(List<MailMessage> mailMessages, String email, String filterString) {
      MailMessage mailMessage = mailMessages.stream()
              .filter((m) -> m.to.equals(email))
              .filter((m) -> m.text.contains(filterString))
              .findFirst().get();
      VerbalExpression regex = VerbalExpression.regex().find("http://").nonSpace().oneOrMore().build();
      return regex.getText(mailMessage.text);
   }

   public void userPasswordReset(String username, String email, String password) throws MessagingException, IOException {
      click(By.cssSelector("a[href=\"/mantisbt-2.25.2/manage_overview_page.php\"]"));
      click(By.cssSelector("a[href=\"/mantisbt-2.25.2/manage_user_page.php\"]"));
      click(By.xpath("//a[text()=\"" + username + "\"]"));
      click(By.xpath("//form[@id=\"manage-user-reset-form\"]//input[@type=\"submit\"]"));
      List<MailMessage> mailMessages = app.mail().waitForMail(1, 10000);
      String resetLink = findLink(mailMessages, email, "Your password has been reset");
      finish(resetLink, password);
   }
}
