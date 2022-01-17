package java_practice.addressbook.model;

import com.google.gson.annotations.Expose;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.File;
import java.util.Objects;

@Entity
@Table(name = "addressbook")
public class ContactData {
   @Id
   @Column(name = "id")
   private int id = Integer.MAX_VALUE;

   @Expose
   @Column(name = "firstname")
   private String firstName;

   @Expose
   @Column(name = "lastname")
   private String lastName;

   @Expose
   @Column(name = "address")
   @Type(type = "text")
   private String address;

   @Expose
   @Column(name = "email")
   @Type(type = "text")
   private String firstMail;

   @Expose
   @Column(name = "email2")
   @Type(type = "text")
   private String secondMail;

   @Expose
   @Column(name = "email3")
   @Type(type = "text")
   private String thirdMail;

   @Expose
   @Column(name = "home")
   @Type(type = "text")
   private String homePhone;

   @Expose
   @Column(name = "mobile")
   @Type(type = "text")
   private String mobilePhone;

   @Expose
   @Column(name = "work")
   @Type(type = "text")
   private String workPhone;

   @Expose
   @Transient
   private String group;

   @Expose
   @Transient
   private String allEmails;

   @Expose
   @Transient
   private String allPhones;

   @Expose
   @Column(name = "photo")
   @Type(type = "text")
   private String photo;

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

   public ContactData withAllPhones(String allPhones) {
      this.allPhones = allPhones;
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
      this.photo = photo.getPath();
      return this;
   }

   public ContactData withId(int id) {
      this.id = id;
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
      if(photo == null)
         return null;
      return new File(photo);
   }

   public int getId() {
      return id;
   }

   @Override
   public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;
      ContactData that = (ContactData) o;
      return id == that.id && Objects.equals(firstName, that.firstName) && Objects.equals(lastName, that.lastName);
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
   public int hashCode() {
      return Objects.hash(id, firstName, lastName);
   }
}
