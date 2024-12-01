package com.example.blooddrop;

public class usersHelperClass {


    String Name, Email, username,nic,age,city,mobile_number;


    public usersHelperClass() {
    }

    public usersHelperClass(String name, String email, String username, String nic, String age, String city, String mobile_number) {
        Name = name;
        Email = email;
        this.username = username;

        this.nic = nic;
        this.age = age;
        this.city = city;
        this.mobile_number = mobile_number;
    }

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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


    public String getNic() {
        return nic;
    }

    public void setNic(String nic) {
        this.nic = nic;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getMobile_number() {
        return mobile_number;
    }

    public void setMobile_number(String mobile_number) {
        this.mobile_number = mobile_number;
    }
}
