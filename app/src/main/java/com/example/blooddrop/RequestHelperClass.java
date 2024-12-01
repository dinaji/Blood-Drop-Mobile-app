package com.example.blooddrop;

public class RequestHelperClass {
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNic() {
        return nic;
    }

    public void setNic(String nic) {
        this.nic = nic;
    }

    public String getBloodtype() {
        return bloodtype;
    }

    public void setBloodtype(String bloodtype) {
        this.bloodtype = bloodtype;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getMobile_number() {
        return mobile_number;
    }

    public void setMobile_number(String mobile_number) {
        this.mobile_number = mobile_number;
    }

    public String getFileUri() {
        return fileUri;
    }

    public void setFileUri(String fileUri) {
        this.fileUri = fileUri;
    }

    public RequestHelperClass() {

    }

    String name;
    String email;
    String nic;
    String bloodtype;
    String location;
    String age;

    public RequestHelperClass(String name, String email, String nic, String bloodtype, String location, String age, String mobile_number, String fileUri) {
        this.name = name;
        this.email = email;
        this.nic = nic;
        this.bloodtype = bloodtype;
        this.location = location;
        this.age = age;
        this.mobile_number = mobile_number;
        this.fileUri = fileUri;
    }

    String mobile_number;
    String fileUri;
}
