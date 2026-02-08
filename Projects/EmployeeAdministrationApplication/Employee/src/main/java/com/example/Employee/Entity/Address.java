package com.example.Employee.Entity;

import jakarta.persistence.*;

@Entity
@Table(name ="Address")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  int id;
    private  String line1;
    private  String line2;
    private  String zipcode;
    private  String city;

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Address() {

    }
    @ManyToOne
    private  Employee employee;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLine1() {
        return line1;
    }

    public void setLine1(String line1) {
        this.line1 = line1;
    }

    public String getLine2() {
        return line2;
    }

    public void setLine2(String line2) {
        this.line2 = line2;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Address(int id, String line1, String line2, String zipcode, String city, String state, String country) {
        this.id = id;
        this.line1 = line1;
        this.line2 = line2;
        this.zipcode = zipcode;
        this.city = city;
        this.state = state;
        this.country = country;
    }

    private  String state;
    private  String country;
}
