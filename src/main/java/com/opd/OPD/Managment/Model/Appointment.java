package com.opd.OPD.Managment.Model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
public class Appointment {
    @Id
    @GeneratedValue
    public int id ;

    public String date;

    @ManyToOne
    @JoinColumn(name = "patient_id")
    @JsonIgnoreProperties("appointments")
    public Patient patient;

    @ManyToOne
    @JoinColumn(name = "doctor_id")
    @JsonIgnoreProperties("appointments")
    public Employee employee;

    @OneToMany(mappedBy = "appointment")
    @JsonIgnoreProperties("appointment")
    public List<Attachment> attachments;

    @ManyToMany
    @JoinTable(
            name="appointment_symptom",
            joinColumns = @JoinColumn(name = "appointment_id"),
            inverseJoinColumns = @JoinColumn(name = "symptom_id")
    )
    @JsonIgnoreProperties("appointments")
    public List<Symptoms> symptoms;

    @ManyToMany
    @JoinTable(
            name= "appointment_medicine",
            joinColumns = @JoinColumn(name = "appointment_id"),
            inverseJoinColumns = @JoinColumn(name = "medicine_id")
    )
    @JsonIgnoreProperties("appointments")
    public List<Medicine> medicines;

    @ManyToMany
    @JoinTable(
            name = "appointment_diagnosis",
            joinColumns = @JoinColumn(name = "appointment_id"),
            inverseJoinColumns = @JoinColumn(name = "diagnosis_id")
    )
    @JsonIgnoreProperties("appointments")
    public List<Diagnosis> diagnosis;
}
