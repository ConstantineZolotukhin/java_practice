package java_practice.addressbook.model;

import java.util.Objects;

public class ContactData {
   private int id = Integer.MAX_VALUE;;
   private String firstName;
   private String lastName;
   private String address;
   private String mail;
   private String phone;
   private String group;

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
   public ContactData withMail(String mail) {
      this.mail = mail;
      return this;
   }
   public ContactData withPhone(String phone) {
      this.phone = phone;
      return this;
   }
   public ContactData withGroup(String group) {
      this.group = group;
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

   public String getMail() {
      return mail;
   }

   public int getId() {
      return id;
   }

   public String getPhone() {
      return phone;
   }

   public String getGroup() {
      return group;
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
