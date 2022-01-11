package java_practice.addressbook.model;

import java.io.File;
import java.util.Objects;

public class ContactData {
   private int id = Integer.MAX_VALUE;;
   private String firstName;
   private String lastName;
   private String address;
   private String firstMail;
   private String secondMail;
   private String thirdMail;
   private String homePhone;
   private String mobilePhone;
   private String workPhone;
   private String allPhones;
   private String group;
   private String allEmails;
   private File photo;

   public ContactData withAllPhones(String allPhones) {
      this.allPhones = allPhones;
      return this;
   }

   public ContactData withId(int id) {
      this.id = id;
      return this;
   }

   public ContactData withFirstName(String firstName) {
      this.firstName = firstName;
      return this;
   }

   public ContactData withLastName(String lastName) {
      this.lastName = lastName;
      return this;
   }

   public ContactData withAddress(String address) {
      this.address = address;
      return this;
   }

   public ContactData withFirstEmail(String firstMail) {
      this.firstMail = firstMail;
      return this;
   }

   public ContactData withSecondEmail(String secondMail) {
      this.secondMail = secondMail;
      return this;
   }

   public ContactData withThirdEmail(String thirdMail) {
      this.thirdMail = thirdMail;
      return this;
   }

   public ContactData withHomePhone(String homePhone) {
      this.homePhone = homePhone;
      return this;
   }

   public ContactData withMobilePhone(String mobilePhone) {
      this.mobilePhone = mobilePhone;
      return this;
   }

   public ContactData withWorkPhone(String workPhone) {
      this.workPhone = workPhone;
      return this;
   }

   public ContactData withGroup(String group) {
      this.group = group;
      return this;
   }

   public ContactData withAllEmails(String allEmails) {
      this.allEmails = allEmails;
      return this;
   }

   public ContactData withPhoto(File photo) {
      this.photo = photo;
      return this;
   }

   public String getFirstName() {
      return firstName;
   }

   public String getLastName() {
      return lastName;
   }

   public String getAddress() {
      return address;
   }

   public String getFirstEmail() {
      return firstMail;
   }

   public String getSecondEmail() {
      return secondMail;
   }

   public String getThirdEmail() {
      return thirdMail;
   }

   public int getId() {
      return id;
   }

   public String getHomePhone() {
      return homePhone;
   }

   public String getMobilePhone() {
      return mobilePhone;
   }

   public String getWorkPhone() {
      return workPhone;
   }

   public String getGroup() {
      return group;
   }

   public String getAllPhones() {
     return allPhones;
   }

   public String getAllEmails() {
      return allEmails;
   }

   public File getPhoto() {
      return photo;
   }

   @Override
   public String toString() {
      return "ContactData{" +
              "id='" + id + '\'' +
              ", firstName='" + firstName + '\'' +
              ", lastName='" + lastName + '\'' +
              '}';
   }

   @Override
   public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;
      ContactData that = (ContactData) o;
      return id == that.id && Objects.equals(firstName, that.firstName) && Objects.equals(lastName, that.lastName);
   }

   @Override
   public int hashCode() {
      return Objects.hash(id, firstName, lastName);
   }


}
