package com.opd.OPD.Managment.Model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class Employee {
    @Id
    @GeneratedValue
    public int id ;

    public String name;
    public String password;
    public String contact;
    public String email;
    public String address;

    @ManyToOne
    @JoinColumn(name = "designation_id")
    @JsonIgnoreProperties("employees")
    public Designation employee_designation;

    @ManyToOne
    @JoinColumn(name = "department_id")
    @JsonIgnoreProperties("employees")
    public Department employee_department;

    @ManyToOne
    @JoinColumn(name = "occupation_id")
    @JsonIgnoreProperties("employees")
    public Occupation employee_occupation;

    @OneToMany(mappedBy = "employee")
    @JsonIgnoreProperties("employee")
    public List<Appointment> appointments;
}
