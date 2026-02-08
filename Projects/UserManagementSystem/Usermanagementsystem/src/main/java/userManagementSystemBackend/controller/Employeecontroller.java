package userManagementSystemBackend.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import userManagementSystemBackend.dto.Employeedto;
import userManagementSystemBackend.entity.Employee;
import userManagementSystemBackend.repository.Employeerepository;
import userManagementSystemBackend.service.Employeeservice;
import java.util.List;

@CrossOrigin("*")
@AllArgsConstructor
@RestController
@RequestMapping("/api/employees")
public class Employeecontroller {

    private Employeeservice employeeservice;

    //Build add employee Rest API
    @PostMapping
    public ResponseEntity<Employeedto> createEmployee(@RequestBody Employeedto employeedto) {
        Employeedto savedEmployee = employeeservice.createEmployee(employeedto);
        return new ResponseEntity<>(savedEmployee, HttpStatus.CREATED);
    }

    //Build Get employee Rest API
    @GetMapping("{id}")
    public ResponseEntity<Employeedto> getEmployeeById(@PathVariable("id") Long employeeId) {
        Employeedto employeedto = employeeservice.getEmployeeById(employeeId);
        return ResponseEntity.ok(employeedto);
    }
    //Build Get All employees Rest API
    @GetMapping
    public ResponseEntity<List<Employeedto>> getAllEmployees() {
        List<Employeedto> employees = employeeservice.getAllEmployees();
        return ResponseEntity.ok(employees);
    }
    //Build Update employee Rest API
    @PutMapping("{id}")
    public ResponseEntity<Employeedto>  updateEmployee(@PathVariable("id") Long employeeId,@RequestBody Employeedto updatedEmployee) {
        Employeedto employeedto = employeeservice.updateEmployee(employeeId, updatedEmployee);
        return ResponseEntity.ok(employeedto);


}   @DeleteMapping("{id}")
    public ResponseEntity<String> deleteEmployeeById(@PathVariable("id") Long employeeId) {
      employeeservice.deleteEmployee(employeeId);
        return ResponseEntity.ok("Employee deleted successfully!");
    }
}