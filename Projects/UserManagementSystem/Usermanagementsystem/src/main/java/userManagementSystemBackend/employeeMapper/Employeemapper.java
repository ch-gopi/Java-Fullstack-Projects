<<<<<<< HEAD
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
=======
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
>>>>>>> 7597e7e0eba66a899deb947e73815869450259fd
