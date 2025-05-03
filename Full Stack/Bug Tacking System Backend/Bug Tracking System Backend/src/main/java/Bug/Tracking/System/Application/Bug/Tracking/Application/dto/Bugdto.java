package Bug.Tracking.System.Application.Bug.Tracking.Application.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDate;
import Bug.Tracking.System.Application.Bug.Tracking.Application.Enums.Severity;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Bugdto {
    private Long id;
    private String title;
    private String description;
    private boolean completed;

    private LocalDate fromDate;
    private LocalDate toDate;
    private Severity severity;
    private Long userId;

}
