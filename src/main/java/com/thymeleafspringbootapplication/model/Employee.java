package com.thymeleafspringbootapplication.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "employee")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "employee_id")
    private long id;

    @Column(name = "employee_name")
    private String name;

    @Column(name = "employee_contact")
    private String contact;

    @Column(name = "employee_password")
    private String password;

    @Column(name = "employee_Salary")
    private long salary;

    @Column(name = "employee_Address")
    private String address;

    // Constructors
    public Employee() {
    }

    public Employee(String name, String contact, String password, long salary, String address) {
        this.name = name;
        this.contact = contact;
        this.password = password;
        this.salary = salary;
        this.address = address;
    }

    // Getters and Setters
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public long getSalary() {
        return salary;
    }

    public void setSalary(long salary) {
        this.salary = salary;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
