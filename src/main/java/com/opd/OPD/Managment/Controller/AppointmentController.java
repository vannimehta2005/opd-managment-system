package com.opd.OPD.Managment.Controller;

import com.opd.OPD.Managment.DTO.CreateUpdateAppointmentDto;
import com.opd.OPD.Managment.Model.Appointment;
import com.opd.OPD.Managment.Repository.IAppointmentRepo;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequestMapping("api/appointment")
@RestController

public class AppointmentController {
    public IAppointmentRepo appointmentController ;
    public AppointmentController(IAppointmentRepo appointmentController){
        this.appointmentController = appointmentController;
    }

    @PostMapping
    public ResponseEntity<String> addAppointment(@RequestBody CreateUpdateAppointmentDto createAppointmentDto){
        Appointment appointmentObj = new Appointment();
        appointmentObj.date=createAppointmentDto.date;
        appointmentController.save(appointmentObj);
        return ResponseEntity.ok("Appointment added successfully!");
    }

    @GetMapping
    public ResponseEntity<List<Appointment>> getAllAppointments(){
        List<Appointment> appointments = appointmentController.findAll();
        return ResponseEntity.ok(appointments);
    }


    @PutMapping("{id}")
    public ResponseEntity<String> updateAppointment(@PathVariable Integer id , @RequestBody CreateUpdateAppointmentDto updateAppointmentDto){
        Optional<Appointment> existingAppointment = appointmentController.findById(id);
        if (existingAppointment.isEmpty()){
            return ResponseEntity.badRequest().body("Appointment not found!");
        }else{
            existingAppointment.get().date = updateAppointmentDto.date;
            appointmentController.save(existingAppointment.get());
            return ResponseEntity.ok("Appointment updated successfully!");
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteAppointmentById(@PathVariable Integer id){
        Optional<Appointment> existingAppointment = appointmentController.findById(id);
        if (existingAppointment.isEmpty()){
            return ResponseEntity.badRequest().body("Appointment not found!");
        }else{
            appointmentController.deleteById(id);
            return ResponseEntity.ok("Appointment deleted successfully!");
        }
    }
}
