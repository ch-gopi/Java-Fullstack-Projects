package com.example.Employee.controller;
import com.example.Employee.Entity.Employee;
import com.example.Employee.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
@ResponseBody
//@RestController
public class EmployeeController {
    @Autowired
    EmployeeService employeeService;
    @RequestMapping("/employees")
     public List<Employee> findallemployees(){

         return employeeService.getAllEmployees();
    }
    @RequestMapping(value = "/employees",method = RequestMethod.POST)
    public void createEmployee(@RequestBody Employee employee){
        employeeService.createEmployee(employee);
    }
    @RequestMapping("/employees/{id}")
    public Employee findAnEmployee(@PathVariable int id){
        return employeeService.getAnEmployee(id);
    }
    @RequestMapping(value = "/employees/{id}",method = RequestMethod.PUT)
    public void updateEmployee(@PathVariable int id,@RequestBody Employee employee){
        employeeService.updateEmployee(employee);
    }
    @RequestMapping(value = "employees/{id}",method = RequestMethod.DELETE)
    public List<Employee> deleteEmployee(@PathVariable int id){
        employeeService.deleteEmployee(id);
        return  employeeService.getAllEmployees();
    }
}
