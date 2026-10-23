package com.opd.OPD.Managment.Controller;

import com.opd.OPD.Managment.DTO.CreateUpdateDiagnosisDto;
import com.opd.OPD.Managment.Model.Diagnosis;
import com.opd.OPD.Managment.Repository.IDiagnosisRepo;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequestMapping("api/diagnosis")
@RestController

public class DiagnosisController {
    public IDiagnosisRepo diagnosisController;
    public DiagnosisController(IDiagnosisRepo diagnosisController){
        this.diagnosisController = diagnosisController ;
    }

    @PostMapping
    public ResponseEntity<String> addDiagnosis(@RequestBody CreateUpdateDiagnosisDto createDiagnosisDto){
        Diagnosis diagnosisObj = new Diagnosis();
        diagnosisObj.name = createDiagnosisDto.name;
        diagnosisObj.remarks = createDiagnosisDto.remarks;
        diagnosisController.save(diagnosisObj);
        return ResponseEntity.ok("Diagnosis Added successfully!");
    }

    @GetMapping
    public ResponseEntity<List<Diagnosis>> getAllDiagnosis(){
        List<Diagnosis> diagnoses = diagnosisController.findAll();
        return ResponseEntity.ok(diagnoses);
    }

    @PutMapping("{id}")
    public ResponseEntity<String> updateDiagnosis(@PathVariable Integer id , @RequestBody CreateUpdateDiagnosisDto updateDiagnosisDto){
        Optional<Diagnosis> existingDiagnosis = diagnosisController.findById(id);
        if(!existingDiagnosis.isPresent()){
            return ResponseEntity.badRequest().body("Diagnosis Not Found");
        }else{
            existingDiagnosis.get().name = updateDiagnosisDto.name;
            existingDiagnosis.get().remarks = updateDiagnosisDto.remarks;
            diagnosisController.save(existingDiagnosis.get());
            return ResponseEntity.ok("Diagnosis updated successfully!");
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteDiagnosisById(@PathVariable Integer id){
        Optional<Diagnosis> existingDiagnosis = diagnosisController.findById(id);
        if(!existingDiagnosis.isPresent()){
            return ResponseEntity.badRequest().body("Diagnosis Not Found");
        }else{
            diagnosisController.deleteById(id);
            return ResponseEntity.ok("Diagnosis deleted successfully");
        }
    }

}
