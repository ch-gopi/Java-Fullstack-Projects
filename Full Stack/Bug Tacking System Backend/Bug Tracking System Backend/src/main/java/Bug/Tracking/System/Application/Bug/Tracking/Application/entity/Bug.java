package Bug.Tracking.System.Application.Bug.Tracking.Application.entity;

import Bug.Tracking.System.Application.Bug.Tracking.Application.Enums.Severity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "bugs")
public class Bug {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String description;

    @Column
    private Boolean completed;

    @Column
    private LocalDate fromDate;

    @Column
    private LocalDate toDate;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Severity severity;
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

}
