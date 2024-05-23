package com.example.Employee.Entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int employeeId;
    String employeeName;
    String employeeCity;



    @OneToMany(cascade = CascadeType.ALL)
    private List<Address> Addresses;

    public Employee(int i, String gopi, String hyderabad) {

    }

    public Employee() {

    }

    public List<Address> getAddresses() {
        return Addresses;
    }

    public void setAddresses(List<Address> addresses) {
        Addresses = addresses;
    }
    public com.example.Employee.Entity.Spouse getSpouse() {
        return Spouse;
    }

    public void setSpouse(com.example.Employee.Entity.Spouse spouse) {
        Spouse = spouse;
    }

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_spouse")
    private  Spouse Spouse;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name="employee_project",joinColumns =@JoinColumn(name ="fk_employee"),
    inverseJoinColumns = @JoinColumn(name = "fk_project"))
    private List<Project> projects;

    public Employee(int employeeId, String employeeName, String employeeCity, List<Project> projects) {
        this.employeeId=employeeId;
        this.employeeCity=employeeCity;
        this.employeeName=employeeName;
        this.projects = projects;
    }

    public Employee(List<Project> projects) {

        this.projects = projects;
    }

    public int getEmployeeId(){
        return  employeeId;
    }
    public void setEmployeeId(int employeeId){
        this.employeeId=employeeId;
    }
    public String getEmployeeName(){
        return  employeeName;
    }


    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getEmployeeCity() {
        return employeeCity;
    }

    public void setEmployeeCity(String employeeCity) {
        this.employeeCity = employeeCity;
    }


    public List<Project> getProjects() {
        return projects;
    }

    public void setProjects(List<Project> projects) {
        this.projects = projects;
    }
}
