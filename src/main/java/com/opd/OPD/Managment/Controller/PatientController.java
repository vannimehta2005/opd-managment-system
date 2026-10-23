package com.opd.OPD.Managment.Controller;

import com.opd.OPD.Managment.DTO.CreateUpdatePatientDto;
import com.opd.OPD.Managment.Model.Patient;
import com.opd.OPD.Managment.Repository.IPatientRepo;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequestMapping("api/patient")
@RestController

public class PatientController {
    public IPatientRepo patientController;
    public PatientController ( IPatientRepo patientController){
        this.patientController = patientController;
    }

    @PostMapping
    public ResponseEntity<String> addPatient(@RequestBody CreateUpdatePatientDto createPatientDto){
        Patient patientObj = new Patient();
        patientObj.name = createPatientDto.name;
        patientObj.address = createPatientDto.address;
        patientObj.dob = createPatientDto.dob;
        patientObj.gender = createPatientDto.gender;
        patientController.save(patientObj);
        return ResponseEntity.ok("Patient added successfully !");
    }

    @GetMapping
    public ResponseEntity<List<Patient>> getAllPatient(){
        List<Patient> patients = patientController.findAll();
        return ResponseEntity.ok(patients);
    }

    @PutMapping ("{id}")
    public ResponseEntity<String> updatePatient(@PathVariable Integer id , @RequestBody CreateUpdatePatientDto updatePatientDto) {
        Optional<Patient> existingPatient = patientController.findById(id);
        if (!existingPatient.isPresent()) {
            return ResponseEntity.badRequest().body("Patient Not Found!");
        } else {
            existingPatient.get().gender = updatePatientDto.gender;
            existingPatient.get().dob = updatePatientDto.dob;
            existingPatient.get().name = updatePatientDto.name;
            existingPatient.get().address = updatePatientDto.address;
            patientController.save(existingPatient.get());
            return ResponseEntity.ok("Patient Updated Successfully!");
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deletePatientById (@PathVariable Integer id ){
        Optional<Patient> existingPatient = patientController.findById(id);
        if (!existingPatient.isPresent()) {
            return ResponseEntity.badRequest().body("Patient Not Found!");
        }else {
            patientController.deleteById(id);
            return ResponseEntity.ok("Patient Removed Successfully!");
        }
    }


}
