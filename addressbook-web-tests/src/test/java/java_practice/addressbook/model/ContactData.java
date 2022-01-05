package java_practice.addressbook.model;

import java.util.Objects;

public class ContactData {
   private int id;
   private final String firstName;
   private final String lastName;
   private final String address;
   private final String mail;
   private final String phone;
   private String group;

   public void setId(int id) {
      this.id = id;
   }

   public ContactData(int id, String firstName, String lastName, String address, String mail, String phone, String group) {
      this.id = id;
      this.firstName = firstName;
      this.lastName = lastName;
      this.address = address;
      this.mail = mail;
      this.phone = phone;
      this.group = group;
   }

   public ContactData(int id, String firstName, String lastName) {
      this.id = id;
      this.firstName = firstName;
      this.lastName = lastName;
      this.address = null;
      this.mail = null;
      this.phone = null;
      this.group = null;
   }

   public ContactData(String firstName, String lastName, String address, String mail, String phone, String group) {
      this.id = Integer.MAX_VALUE;
      this.firstName = firstName;
      this.lastName = lastName;
      this.address = address;
      this.mail = mail;
      this.phone = phone;
      this.group = group;
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
      return Objects.equals(firstName, that.firstName) && Objects.equals(lastName, that.lastName);
   }

   @Override
   public int hashCode() {
      return Objects.hash(firstName, lastName);
   }

}
