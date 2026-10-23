package com.opd.OPD.Managment.Controller;

import com.opd.OPD.Managment.DTO.CreateEmployeeDto;
import com.opd.OPD.Managment.DTO.UpdateEmployeeDto;
import com.opd.OPD.Managment.DTO.UpdateEmployeePasswordDto;
import com.opd.OPD.Managment.Model.Employee;
import com.opd.OPD.Managment.Model.Patient;
import com.opd.OPD.Managment.Repository.IEmployeeRepo;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequestMapping("api/employee")
@RestController
public class EmployeeController {
    public IEmployeeRepo employeeController ;
    public EmployeeController(IEmployeeRepo employeeController){
        this.employeeController = employeeController;
    }

    @PostMapping
    public ResponseEntity<String> addEmployee(@RequestBody CreateEmployeeDto employeeDto){
        Employee employeeObj  = new Employee();
        employeeObj.name = employeeDto.name;
        employeeObj.address = employeeDto.address;
        employeeObj.email = employeeDto.email;
        employeeObj.contact = employeeDto.contact;
        employeeObj.password = employeeDto.password;
//        employeeObj.employee_department.id = employeeDto.department_id;
        employeeController.save(employeeObj);
        return ResponseEntity.ok("Employee created successfully");
    }

    @GetMapping
    public ResponseEntity<List<Employee>> getAllEmployees(){
       List<Employee> employees= employeeController.findAll();
        return ResponseEntity.ok(employees);
    }

    @PutMapping("{id}")
    public ResponseEntity<String> updateEmployee(@PathVariable Integer id , @RequestBody UpdateEmployeeDto updateEmployeeDto){
       Optional<Employee> existingEmployee = employeeController.findById(id);
       if (!existingEmployee.isPresent()){
           return ResponseEntity.badRequest().body("Employee Not Found!");
       }
       else {
           existingEmployee.get().contact = updateEmployeeDto.contact;
           existingEmployee.get().email = updateEmployeeDto.email;
           existingEmployee.get().name = updateEmployeeDto.name;
           existingEmployee.get().address = updateEmployeeDto.address;
           employeeController.save(existingEmployee.get());
           return ResponseEntity.ok("Employee details updated successfully");
       }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteEmployeeById( @PathVariable Integer id ){
        Optional<Employee> existingEmployee = employeeController.findById(id);
        if (!existingEmployee.isPresent()){
            return ResponseEntity.badRequest().body("Employee Not Found!");
        }
        else {
            employeeController.deleteById(id);
            return ResponseEntity.ok("Employee Deleted successfully ");
        }
    }

    @PutMapping("/updatepassword/{id}")
    public ResponseEntity<String> updatePassword(@PathVariable Integer id , @RequestBody UpdateEmployeePasswordDto updateEmployeePasswordDto){
        Optional<Employee> existingEmployee = employeeController.findById(id);
        if(!existingEmployee.isPresent()){
            return ResponseEntity.badRequest().body("Employee not found");
        }
        else{
            if (existingEmployee.get().password.equals(updateEmployeePasswordDto.oldPassword)){
                existingEmployee.get().password = updateEmployeePasswordDto.newPassword;
                employeeController.save(existingEmployee.get());
                return ResponseEntity.ok("Password updated successfully!");
            }else{
                return ResponseEntity.badRequest().body("Password entered is incorrect!");
            }

        }
    }
}
