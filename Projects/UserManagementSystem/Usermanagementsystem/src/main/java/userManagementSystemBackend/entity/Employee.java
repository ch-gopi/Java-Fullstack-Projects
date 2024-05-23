package userManagementSystemBackend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name ="employees")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType .IDENTITY)
    private Long id;
    @Column(name ="first_name")
    private String firstName;
    @Column(name ="second_name")
    private String lastName;
    @Column(name ="email",nullable = false,unique = true)
    private String email;
}
