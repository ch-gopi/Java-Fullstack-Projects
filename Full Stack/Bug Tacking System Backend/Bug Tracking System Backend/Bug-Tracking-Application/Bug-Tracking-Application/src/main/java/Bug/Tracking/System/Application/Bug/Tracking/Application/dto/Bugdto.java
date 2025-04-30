package Bug.Tracking.System.Application.Bug.Tracking.Application.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Bugdto {
    private Long id;

    private String title;

    private String description;
    private boolean completed;
}
