package com.opd.OPD.Managment.Model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import java.util.List;

@Entity
public class Designation {
    @Id
    @GeneratedValue
    public int id;

    public String name;

    @OneToMany(mappedBy = "employee_designation")
    @JsonIgnoreProperties("employee_designation")
    public List<Employee> employees;

}
