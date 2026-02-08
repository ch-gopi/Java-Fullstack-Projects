<<<<<<< HEAD
package userManagementSystemBackend.service.employeeServiceImpl;

import lombok.AllArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;
import userManagementSystemBackend.dto.Employeedto;
import userManagementSystemBackend.employeeMapper.Employeemapper;
import userManagementSystemBackend.entity.Employee;
import userManagementSystemBackend.exception.Resourcenotfoundexception;
import userManagementSystemBackend.repository.Employeerepository;
import userManagementSystemBackend.service.Employeeservice;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class Employeeserviceimpl implements Employeeservice {

    private Employeerepository employeeRepository;
    @Override
    public Employeedto createEmployee(Employeedto employeeDto) {
        Employee employee= Employeemapper.mapToEmployee(employeeDto);
        Employee savedEmployee= employeeRepository.save(employee);
        return Employeemapper.mapToEmployeedto(savedEmployee);

    }

    @Override
    public Employeedto getEmployeeById(Long employeeId) {
        Employee employee =employeeRepository.findById(employeeId).orElseThrow(()-> new Resourcenotfoundexception("employee doesn't exist with given id"+ employeeId));
        return Employeemapper.mapToEmployeedto(employee);
    }
    public List<Employeedto> getAllEmployees(){
        List<Employee> employees= employeeRepository.findAll();
        return employees.stream().map((employee) -> (Employeemapper.mapToEmployeedto(employee))).collect(Collectors.toList());


    }

    @Override
    public Employeedto updateEmployee(Long employeeId, Employeedto updatedEmployee) {
        Employee employee =employeeRepository.findById(employeeId).orElseThrow(()-> new Resourcenotfoundexception("employee doesn't exist with given id"+ employeeId));
        employee.setFirstName(updatedEmployee.getFirstName());
        employee.setLastName(updatedEmployee.getLastName());
        employee.setEmail(updatedEmployee.getEmail());
        Employee updatedEmployeeobj = employeeRepository.save(employee);

        return Employeemapper.mapToEmployeedto(updatedEmployeeobj);

    }

    @Override
    public void deleteEmployee(Long employeeId) {
        Employee employee =employeeRepository.findById(employeeId).orElseThrow(()-> new Resourcenotfoundexception("employee doesn't exist with given id"+ employeeId));
        employeeRepository.deleteById(employeeId);

    }
}
=======
package userManagementSystemBackend.service.employeeServiceImpl;

import lombok.AllArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;
import userManagementSystemBackend.dto.Employeedto;
import userManagementSystemBackend.employeeMapper.Employeemapper;
import userManagementSystemBackend.entity.Employee;
import userManagementSystemBackend.exception.Resourcenotfoundexception;
import userManagementSystemBackend.repository.Employeerepository;
import userManagementSystemBackend.service.Employeeservice;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class Employeeserviceimpl implements Employeeservice {

    private Employeerepository employeeRepository;
    @Override
    public Employeedto createEmployee(Employeedto employeeDto) {
        Employee employee= Employeemapper.mapToEmployee(employeeDto);
        Employee savedEmployee= employeeRepository.save(employee);
        return Employeemapper.mapToEmployeedto(savedEmployee);

    }

    @Override
    public Employeedto getEmployeeById(Long employeeId) {
        Employee employee =employeeRepository.findById(employeeId).orElseThrow(()-> new Resourcenotfoundexception("employee doesn't exist with given id"+ employeeId));
        return Employeemapper.mapToEmployeedto(employee);
    }
    public List<Employeedto> getAllEmployees(){
        List<Employee> employees= employeeRepository.findAll();
        return employees.stream().map((employee) -> (Employeemapper.mapToEmployeedto(employee))).collect(Collectors.toList());


    }

    @Override
    public Employeedto updateEmployee(Long employeeId, Employeedto updatedEmployee) {
        Employee employee =employeeRepository.findById(employeeId).orElseThrow(()-> new Resourcenotfoundexception("employee doesn't exist with given id"+ employeeId));
        employee.setFirstName(updatedEmployee.getFirstName());
        employee.setLastName(updatedEmployee.getLastName());
        employee.setEmail(updatedEmployee.getEmail());
        Employee updatedEmployeeobj = employeeRepository.save(employee);

        return Employeemapper.mapToEmployeedto(updatedEmployeeobj);

    }

    @Override
    public void deleteEmployee(Long employeeId) {
        Employee employee =employeeRepository.findById(employeeId).orElseThrow(()-> new Resourcenotfoundexception("employee doesn't exist with given id"+ employeeId));
        employeeRepository.deleteById(employeeId);

    }
}
>>>>>>> 7597e7e0eba66a899deb947e73815869450259fd
