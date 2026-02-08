package userManagementSystemBackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import userManagementSystemBackend.entity.Employee;

public interface Employeerepository extends JpaRepository <Employee,Long>{

}
