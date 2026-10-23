package com.opd.OPD.Managment.Model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
public class Patient {
    @Id
    @GeneratedValue
    @Column(name = "uhid")
    public int uhid ;

    public String name;
    public String address;
    public String gender;
    public LocalDate dob;

    @OneToMany(mappedBy = "patient")
    @JsonIgnoreProperties("patient")
    public List<Appointment> appointments;

}
