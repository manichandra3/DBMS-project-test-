package com.thymeleafspringbootapplication.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.persistence.*;;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
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


    public Employee(String name, String contact, String password, long salary, String address) {
        this.name = name;
        this.contact = contact;
        this.password = password;
        this.salary = salary;
        this.address = address;
    }
}
