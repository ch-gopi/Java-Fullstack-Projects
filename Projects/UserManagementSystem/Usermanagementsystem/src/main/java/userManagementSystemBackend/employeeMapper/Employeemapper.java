package userManagementSystemBackend.employeeMapper;

import userManagementSystemBackend.dto.Employeedto;
import userManagementSystemBackend.entity.Employee;

public class Employeemapper {
    public static Employeedto mapToEmployeedto(Employee employee) {
        return new Employeedto(
                employee.getId(),
                employee.getFirstName(),
                employee.getLastName(),
                employee.getEmail()

        );
    }

    public static Employee mapToEmployee(Employeedto employeeDto) {
        return new Employee(
                employeeDto.getId(),
                employeeDto.getFirstName(),
                employeeDto.getLastName(),
                employeeDto.getEmail()

        );
    }
}
