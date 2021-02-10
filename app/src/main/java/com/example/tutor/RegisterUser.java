package com.example.tutor;



public class RegisterUser {
    String Name, Email, Phone, Qulification, Address, experties, charges, image;


    public RegisterUser() {

    }

    public RegisterUser(String name, String email, String phone, String qulification, String address, String experties, String charges ,String image) {
        Name = name;
        Phone = phone;
        Email = email;
        Qulification = qulification;
        Address = address;
        this.experties = experties;
        this.charges = charges;
            this.image = image;



    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
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

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public String getQulification() {
        return Qulification;
    }

    public void setQulification(String qulification) {
        Qulification = qulification;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getExperties() {
        return experties;
    }

    public void setExperties(String experties) {
        this.experties = experties;
    }

    public String getCharges() {
        return charges;
    }

    public void setCharges(String charges) {
        this.charges = charges;
    }
}
