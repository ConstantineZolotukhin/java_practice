package java_practice.addressbook.model;

public class ContactData {
   private final String firstName;
   private final String lastName;
   private final String address;
   private final String mail;
   private final String phone;

   public ContactData(String firstName, String lastName, String address, String mail, String phone) {
      this.firstName = firstName;
      this.lastName = lastName;
      this.address = address;
      this.mail = mail;
      this.phone = phone;
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

   public String getPhone() {
      return phone;
   }
}
