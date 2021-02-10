package com.example.tutor;

public class StudentUser {
    String Name, Email, Phone, Address,Subject;

    public StudentUser() {
    }

    public StudentUser(String name, String email, String phone, String address, String subject) {
        Name = name;
        Email = email;
        Phone = phone;
        Address = address;
        Subject = subject;
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

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getSubject() {
        return Subject;
    }

    public void setSubject(String subject) {
        Subject = subject;
    }
}
