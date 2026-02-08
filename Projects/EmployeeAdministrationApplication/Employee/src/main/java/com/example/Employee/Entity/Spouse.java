<<<<<<< HEAD
package com.example.Employee.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "spouse")
public class Spouse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  int id;
    private String name;
    private String mobileno;
    private int age;

    public Spouse(int id, String name, String mobileno, int age) {
        this.id = id;
        this.name = name;
        this.mobileno = mobileno;
        this.age = age;
    }

@OneToOne(mappedBy = "Spouse")
private  Employee employee;
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

    public String getMobileno() {
        return mobileno;
    }

    public void setMobileno(String mobileno) {
        this.mobileno = mobileno;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
=======
package com.example.Employee.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "spouse")
public class Spouse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  int id;
    private String name;
    private String mobileno;
    private int age;

    public Spouse(int id, String name, String mobileno, int age) {
        this.id = id;
        this.name = name;
        this.mobileno = mobileno;
        this.age = age;
    }

@OneToOne(mappedBy = "Spouse")
private  Employee employee;
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

    public String getMobileno() {
        return mobileno;
    }

    public void setMobileno(String mobileno) {
        this.mobileno = mobileno;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
>>>>>>> 7597e7e0eba66a899deb947e73815869450259fd
