package com.example.blooddrop;


public class UserHelperClass1 {

    String Name;

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getNIC() {
        return NIC;
    }

    public void setNIC(String NIC) {
        this.NIC = NIC;
    }

    public String getLocation() {
        return Location;
    }

    public void setLocation(String location) {
        Location = location;
    }

    public String getBloodType() {
        return BloodType;
    }

    public void setBloodType(String bloodType) {
        BloodType = bloodType;
    }

    public String getAge() {
        return Age;
    }

    public void setAge(String age) {
        Age = age;
    }

    public String getDonateDate() {
        return DonateDate;
    }

    public void setDonateDate(String donateDate) {
        DonateDate = donateDate;
    }

    public String getPhoneNumber() {
        return PhoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        PhoneNumber = phoneNumber;
    }

    public String getLastDonatedDate() {
        return LastDonatedDate;
    }

    public void setLastDonatedDate(String lastDonatedDate) {
        LastDonatedDate = lastDonatedDate;
    }

    public UserHelperClass1(String name, String email, String NIC, String location, String bloodType, String age, String donateDate, String phoneNumber, String lastDonatedDate) {
        Name = name;
        Email = email;
        this.NIC = NIC;
        Location = location;
        BloodType = bloodType;
        Age = age;
        DonateDate = donateDate;
        PhoneNumber = phoneNumber;
        LastDonatedDate = lastDonatedDate;
    }

    public UserHelperClass1() {

    }

    String Email;
    String NIC;
    String Location;
    String BloodType;
    String Age;
    String DonateDate;
    String PhoneNumber;
    String LastDonatedDate;


}
