<<<<<<< HEAD
package userManagementSystemBackend.service;

import userManagementSystemBackend.dto.Employeedto;

import java.util.List;

public interface Employeeservice {
    Employeedto createEmployee(Employeedto employeeDto);
    Employeedto getEmployeeById(Long employeeId);
    List<Employeedto>getAllEmployees();
    Employeedto updateEmployee(Long employeeId,Employeedto updatedEmployee);
    void  deleteEmployee(Long employeeId);

}
=======
package userManagementSystemBackend.service;

import userManagementSystemBackend.dto.Employeedto;

import java.util.List;

public interface Employeeservice {
    Employeedto createEmployee(Employeedto employeeDto);
    Employeedto getEmployeeById(Long employeeId);
    List<Employeedto>getAllEmployees();
    Employeedto updateEmployee(Long employeeId,Employeedto updatedEmployee);
    void  deleteEmployee(Long employeeId);

}
>>>>>>> 7597e7e0eba66a899deb947e73815869450259fd
