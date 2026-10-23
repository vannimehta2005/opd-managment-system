package com.opd.OPD.Managment.Model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class Diagnosis {
    @Id
    @GeneratedValue
    public int id ;

    public String name;
    public String remarks;

    @ManyToMany(mappedBy = "diagnosis")
    @JsonIgnoreProperties("diagnosis")
    public List<Appointment> appointments;
}
