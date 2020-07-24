package com.example.demo.entities;


import com.sun.istack.NotNull;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name="employees")
public
class Employee {

    @Id   // primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotNull
    @NotBlank(message = "required")
    @Column(name = "firstName")
    private String firstName;
    @NotNull
    @NotBlank(message = "required")
    @Column(name = "lastName")
    private String lastName;

    @Column(name = "salary")
    @Min(value = 1000, message = "le salaire doit >= le smic(1000)")
    private int salary;

    public Employee() { }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }
}
