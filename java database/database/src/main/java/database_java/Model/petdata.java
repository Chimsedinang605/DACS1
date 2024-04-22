package database_java.Model;

public class petdata {
   private int pet_id;
   private String breed;
   private String sex;
   private int age;
   private double price;

   public petdata(int pet_id, String breed, String sex, int age, double price) {
      this.pet_id = pet_id;
      this.breed = breed;
      this.sex = sex;
      this.age = age;
      this.price = price;
   }

   public int getPet_id() {
      return pet_id;
   }

   public void setPet_id(int pet_id) {
      this.pet_id = pet_id;
   }

   public String getBreed() {
      return breed;
   }

   public void setBreed(String breed) {
      this.breed = breed;
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

   public double getPrice() {
      return price;
   }

   public void setPrice(double price) {
      this.price = price;
   }

}
