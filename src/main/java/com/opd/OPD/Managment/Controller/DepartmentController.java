package com.opd.OPD.Managment.Controller;

import com.opd.OPD.Managment.DTO.CreateUpdateDepartmentDto;
import com.opd.OPD.Managment.Model.Department;
import com.opd.OPD.Managment.Repository.IDepartmentRepo;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequestMapping("api/department")
@RestController

public class DepartmentController {
    public IDepartmentRepo departmentController;
    public DepartmentController(IDepartmentRepo departmentController){
        this.departmentController = departmentController;
    }

    @PostMapping
    public ResponseEntity<String> addDepartment(@RequestBody CreateUpdateDepartmentDto createDepartmentDto){
        Department departmentObj = new Department();
        departmentObj.name = createDepartmentDto.name;
        departmentController.save(departmentObj);
        return ResponseEntity.ok("Department added successfully!");
    }

    @GetMapping
    public ResponseEntity<List<Department>> getAllDepartment(){
        List<Department> departments = departmentController.findAll();
        return ResponseEntity.ok(departments);
    }

    @PutMapping("{id}")
    public ResponseEntity<String> updateDepartment(@PathVariable Integer id , @RequestBody CreateUpdateDepartmentDto updateDepartmentDto){
        Optional<Department> existingDepartment = departmentController.findById(id);
        if(existingDepartment.isEmpty()){
            return ResponseEntity.badRequest().body("Department Not Found!");
        }else{
            existingDepartment.get().name = updateDepartmentDto.name;
            departmentController.save(existingDepartment.get());
            return ResponseEntity.ok("Department Updated Successfully!");
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteDepartmentById(@PathVariable Integer id){
        Optional<Department> existingDepartment = departmentController.findById(id);
        if(existingDepartment.isEmpty()){
            return ResponseEntity.badRequest().body("Department Not Found!");
        }else {
            departmentController.deleteById(id);
            return ResponseEntity.ok("Department Deleted Successfully!");
        }

    }

}
