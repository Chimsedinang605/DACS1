package database_java.Model;

import java.util.Date;

public class customerdata {
   private int customer_id;
   private int pet_id;
   private String breed;
   private String sex;
   private int age;
   private int quanity;
   private double price;
   private Date ngay;

   public customerdata(int customer_id, int pet_id,String breed, String sex, int age, int quanity, double price, Date ngay) {
      this.customer_id = customer_id;
      this.pet_id = pet_id;
      this.sex = sex;
      this.age = age;
      this.quanity = quanity;
      this.price = price;
      this.ngay = ngay;
      this.breed = breed;
   }

   public String getBreed() {
      return breed;
   }

   public void setBreed(String breed) {
      this.breed = breed;
   }
   public int getCustomer_id() {
      return customer_id;
   }

   public void setCustomer_id(int customer_id) {
      this.customer_id = customer_id;
   }

   public int getPet_id() {
      return pet_id;
   }

   public void setPet_id(int pet_id) {
      this.pet_id = pet_id;
   }

   public String getSex() {
      return sex;
   }

   public void setSex(String sex) {
      this.sex = sex;
   }

   public int getAge() {
      return age;
   }

   public void setAge(int age) {
      this.age = age;
   }

   public int getQuanity() {
      return quanity;
   }

   public void setQuanity(int quanity) {
      this.quanity = quanity;
   }

   public double getPrice() {
      return price;
   }

   public void setPrice(double price) {
      this.price = price;
   }

   public Date getNgay() {
      return ngay;
   }

   public void setNgay(Date ngay) {
      this.ngay = ngay;
   }
   
}
