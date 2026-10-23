package com.opd.OPD.Managment.Controller;

import com.opd.OPD.Managment.DTO.CreateUpdateMedicineDto;
import com.opd.OPD.Managment.Model.Medicine;
import com.opd.OPD.Managment.Repository.IMedicineRepo;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequestMapping("api/medicine")
@RestController

public class MedicineController {
    public IMedicineRepo medicineController;
    public MedicineController(IMedicineRepo medicineController ){
        this.medicineController = medicineController;
    }

    @PostMapping
    public ResponseEntity<String> addMedicine(@RequestBody CreateUpdateMedicineDto createMedicineDto){
        Medicine medicineObj = new Medicine();
        medicineObj.name = createMedicineDto.name;
        medicineObj.composition = createMedicineDto.composition;
        medicineObj.type = createMedicineDto.type;
        medicineController.save(medicineObj);
        return ResponseEntity.ok("Medicine added successfully!");
    }

    @GetMapping
    public ResponseEntity<List<Medicine>> getAllMedicine(){
        List<Medicine> medicines = medicineController.findAll();
        return ResponseEntity.ok(medicines);
    }

    @PutMapping("{id}")
    public ResponseEntity<String> updateMedicine(@PathVariable Integer id , @RequestBody CreateUpdateMedicineDto updateMedicineDto) {
        Optional<Medicine> existingMedicine = medicineController.findById(id);
        if (!existingMedicine.isPresent()) {
            return ResponseEntity.badRequest().body("Medicine Not Found!");
        } else {
            existingMedicine.get().name = updateMedicineDto.name;
            existingMedicine.get().type = updateMedicineDto.type;
            existingMedicine.get().composition = updateMedicineDto.composition;
            medicineController.save(existingMedicine.get());
            return ResponseEntity.ok("Medicine Updated Successfully");
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteMedicineById(@PathVariable Integer id ){
        Optional<Medicine> existingMedicine = medicineController.findById(id);
        if (!existingMedicine.isPresent()) {
            return ResponseEntity.badRequest().body("Medicine Not Found!");
        }else{
            medicineController.deleteById(id);
            return ResponseEntity.ok("Medicine Deleted Successfully");
        }
        }

}
