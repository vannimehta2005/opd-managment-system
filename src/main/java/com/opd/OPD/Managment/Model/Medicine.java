package com.opd.OPD.Managment.Model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class Medicine {
    @Id
    @GeneratedValue
    public int id ;

    public String name;
    public String type;
    public String composition;

    @ManyToMany(mappedBy = "medicines")
    @JsonIgnoreProperties("medicines") // Prevents Medicine from trying to load Appointment's Medicines again
    public List<Appointment> appointments;
}
