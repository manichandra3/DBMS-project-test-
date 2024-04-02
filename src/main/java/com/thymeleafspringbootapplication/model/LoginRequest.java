package com.thymeleafspringbootapplication.model;

public class LoginRequest {
    String employeeContact;
    String password;

    public LoginRequest() {
    }

    public String getEmployeeContact() {
        return employeeContact;
    }

    public void setEmployeeContact(String employeeContact) {
        this.employeeContact = employeeContact;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LoginRequest(String employeeContact, String password) {
        this.employeeContact = employeeContact;
        this.password = password;
    }
}
