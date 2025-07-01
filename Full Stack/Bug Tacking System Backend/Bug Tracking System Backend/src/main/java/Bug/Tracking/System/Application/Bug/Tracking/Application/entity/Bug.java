
package Bug.Tracking.System.Application.Bug.Tracking.Application.entity;

import Bug.Tracking.System.Application.Bug.Tracking.Application.Enums.Severity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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
    @ManyToOne
    @JoinColumn(name = "sprint_id")
    private Sprint sprint;

    @ElementCollection(fetch = FetchType.EAGER) //Directly previewable images on ui
    @CollectionTable(name = "bug_images", joinColumns = @JoinColumn(name = "bug_id"))
    @Column(name = "image_path")
    private List<String> imagePaths = new ArrayList<>();

    @OneToMany(mappedBy = "bug", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<BugComment> comments;

}
