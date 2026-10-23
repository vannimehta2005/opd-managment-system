package com.opd.OPD.Managment.Controller;

import com.opd.OPD.Managment.DTO.CreateUpdateDesignationDto;
import com.opd.OPD.Managment.Model.Designation;
import com.opd.OPD.Managment.Repository.IDesignationRepo;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequestMapping("api/designation")
@RestController

public class DesignationController {
    public IDesignationRepo designationController;
    public DesignationController(IDesignationRepo designationController){
        this.designationController = designationController;
    }

    @PostMapping
    public ResponseEntity<String> addDesignation(@RequestBody CreateUpdateDesignationDto createDesignationDto){
        Designation designationObj = new Designation();
        designationObj.name= createDesignationDto.name;
        designationController.save(designationObj);
        return ResponseEntity.ok("Designation added successfully!");
    }

    @GetMapping
    public ResponseEntity<List<Designation>> getAllDesignation(){
        List<Designation> designations = designationController.findAll();
        return ResponseEntity.ok(designations);
    }

    @PutMapping("{id}")
    public ResponseEntity<String> updateDesignation(@PathVariable Integer id, @RequestBody CreateUpdateDesignationDto updateDesignationDto){
        Optional<Designation> existingDesignation = designationController.findById(id);
        if(existingDesignation.isEmpty()){
            return ResponseEntity.badRequest().body("Designation not found!");
        }else {
            existingDesignation.get().name = updateDesignationDto.name;
            designationController.save(existingDesignation.get());
            return ResponseEntity.ok("Designation Updated Successfully!");
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteDesignationById(@PathVariable Integer id){
        Optional<Designation> existingDesignation = designationController.findById(id);
        if(existingDesignation.isEmpty()){
            return ResponseEntity.badRequest().body("Designation not found!");
        }else{
            designationController.deleteById(id);
            return ResponseEntity.ok("Designation Deleted Successfully!");
        }
    }
}
