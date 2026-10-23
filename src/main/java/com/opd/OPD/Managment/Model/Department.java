package com.opd.OPD.Managment.Model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import java.util.List;

@Entity
public class Department {
    @Id
    @GeneratedValue
    public int id;

    public String name;

    @OneToMany(mappedBy = "employee_department")
    @JsonIgnoreProperties("employee_department")
    public List<Employee> employees;
}
