package com.opd.OPD.Managment.Model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

@Entity
public class Attachment {
    @Id
    @GeneratedValue
    public int id;

    public String fileURL;
    public String description;

    @ManyToOne
    @JoinColumn(name = "appointment_id")
    @JsonIgnoreProperties("attachments")
    public Appointment appointment;
}
