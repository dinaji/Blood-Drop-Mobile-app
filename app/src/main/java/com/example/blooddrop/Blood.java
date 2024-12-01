package com.example.blooddrop;

public class Blood {

    public String bloodType;
    public String status;


   public Blood() {
       // Default constructor required for calls to DataSnapshot.getValue(Blood.class)
   } public Blood(String bloodType, String status) {
       this.bloodType = bloodType;
       this.status = status;
   }
   public String getBloodType() {
       return bloodType;
   }
   public void setBloodType(String bloodType) {
       this.bloodType = bloodType;
   }
   public String getStatus() {
       return status;
   }
   public void setStatus(String status) {
       this.status = status;
   }
}
