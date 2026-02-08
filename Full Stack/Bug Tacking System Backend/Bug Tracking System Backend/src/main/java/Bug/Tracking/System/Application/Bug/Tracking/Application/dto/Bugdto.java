<<<<<<< HEAD
package Bug.Tracking.System.Application.Bug.Tracking.Application.dto;

import Bug.Tracking.System.Application.Bug.Tracking.Application.entity.Sprint;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

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
    private String userEmail;
    private LocalDate fromDate;
    private LocalDate toDate;
    private Severity severity;
    private Long userId;
    private Long sprintId;
    private List<String> imagePaths;

=======
package Bug.Tracking.System.Application.Bug.Tracking.Application.dto;

import Bug.Tracking.System.Application.Bug.Tracking.Application.entity.Sprint;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

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
    private String userEmail;
    private LocalDate fromDate;
    private LocalDate toDate;
    private Severity severity;
    private Long userId;
    private Long sprintId;
    private List<String> imagePaths;

>>>>>>> 7597e7e0eba66a899deb947e73815869450259fd
}