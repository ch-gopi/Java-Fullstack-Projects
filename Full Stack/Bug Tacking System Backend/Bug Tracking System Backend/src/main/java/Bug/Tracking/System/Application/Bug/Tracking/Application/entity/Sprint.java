package Bug.Tracking.System.Application.Bug.Tracking.Application.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Table(name = "Sprint")
@Entity
public class Sprint {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String sprintName;
    private LocalDate startDate;
    private LocalDate endDate;


}
