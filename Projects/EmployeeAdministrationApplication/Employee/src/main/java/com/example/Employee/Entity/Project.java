package com.example.Employee.Entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "project")
public class Project  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    public  Project(){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    private String name;

    private String clientName;

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    @ManyToMany(mappedBy = "projects")
    private  List<Employee>employees;
    public void removeProject(Project project){
        this.employees.remove(project);
        project.getEmployees().remove(project);
    }
    // public void addProject(Project project){
     //   this.projects.add(project);
      //  project.getEmployees().add(this);
    }





