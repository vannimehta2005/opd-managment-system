package com.opd.OPD.Managment.Controller;

import com.opd.OPD.Managment.DTO.CreateUpdateOccupationDto;
import com.opd.OPD.Managment.Model.Occupation;
import com.opd.OPD.Managment.Repository.IOccupationRepo;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequestMapping("api/occupation")
@RestController
public class OccupationController {
    public IOccupationRepo occupationController ;
    public OccupationController(IOccupationRepo occupationController ){
        this.occupationController = occupationController;
    }

    @PostMapping
    public ResponseEntity<String> addOccupation(@RequestBody CreateUpdateOccupationDto createOccupationDto){
        Occupation occupationObj = new Occupation();
        occupationObj.name= createOccupationDto.name;
        occupationController.save(occupationObj);
        return ResponseEntity.ok("Occupation added successfully ");
    }

    @GetMapping
    public ResponseEntity<List<Occupation>> getAllOccupation(){
        List<Occupation> occupations = occupationController.findAll();
        return ResponseEntity.ok(occupations);
    }

    @PutMapping("{id}")
    public ResponseEntity<String> updateOccupation(@PathVariable Integer id , @RequestBody CreateUpdateOccupationDto updateOccupationDto){
        Optional<Occupation> existingOccupation = occupationController.findById(id);
        if(!existingOccupation.isPresent()){
            return ResponseEntity.badRequest().body("Occupation Not Found !");
        }
        else{
            existingOccupation.get().name= updateOccupationDto.name;
            occupationController.save(existingOccupation.get());
            return ResponseEntity.ok("Occupation Updated successfully");
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteOccupationById( @PathVariable Integer id){
        Optional<Occupation> existingOccupation = occupationController.findById(id);
        if(!existingOccupation.isPresent()){
            return ResponseEntity.badRequest().body("Occupation Not Found !");
        }else{
            occupationController.deleteById(id);
            return ResponseEntity.ok("Occupation Deleted successfully");
        }

    }



}
