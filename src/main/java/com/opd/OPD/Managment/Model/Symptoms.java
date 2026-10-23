package com.opd.OPD.Managment.Model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class Symptoms {
    @Id
    @GeneratedValue
    public int id ;

    public String name;

    @ManyToMany(mappedBy = "symptoms")
    @JsonIgnoreProperties("symptoms")
    public List<Appointment> appointments;
}
