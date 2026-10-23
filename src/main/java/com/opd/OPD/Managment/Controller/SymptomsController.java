package com.opd.OPD.Managment.Controller;

import com.opd.OPD.Managment.DTO.CreateUpdateSymptomsDto;
import com.opd.OPD.Managment.Model.Symptoms;
import com.opd.OPD.Managment.Repository.ISymptomsRepo;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequestMapping("api/symptoms")
@RestController
public class SymptomsController {
    public ISymptomsRepo symptomsController;
    public SymptomsController (ISymptomsRepo symtomsController){
        this.symptomsController =symtomsController;
    }

    @PostMapping
    public ResponseEntity<String> addSymptom(@RequestBody CreateUpdateSymptomsDto createSymptomsDto){
        Symptoms symptomObj = new Symptoms();
        symptomObj.name=createSymptomsDto.name;
        symptomsController.save(symptomObj);
        return ResponseEntity.ok("Symptom Added succesfully");
    }

    @GetMapping
    public ResponseEntity<List<Symptoms>> getallSymptom(){
        List<Symptoms> symptoms = symptomsController.findAll();
        return ResponseEntity.ok(symptoms);
    }

    @PutMapping("{id}")
    public ResponseEntity<String> updateSymptom(@PathVariable Integer id , @RequestBody CreateUpdateSymptomsDto updateSymptomsDto) {
        Optional<Symptoms> existingSymptom = symptomsController.findById(id);
        if (!existingSymptom.isPresent()) {
            return ResponseEntity.badRequest().body("Symptom Not Found!");
        } else {
            existingSymptom.get().name = updateSymptomsDto.name;
            symptomsController.save(existingSymptom.get());
            return ResponseEntity.ok("Symptom Updated Successfully");
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteSymptomById(@PathVariable Integer id ){
        Optional<Symptoms> existingSymptom = symptomsController.findById(id);
        if (!existingSymptom.isPresent()) {
            return ResponseEntity.badRequest().body("Symptom Not Found!");
        }else{
            symptomsController.deleteById(id);
            return ResponseEntity.ok("Symptom Deleted Successfully");
        }
    }
}
