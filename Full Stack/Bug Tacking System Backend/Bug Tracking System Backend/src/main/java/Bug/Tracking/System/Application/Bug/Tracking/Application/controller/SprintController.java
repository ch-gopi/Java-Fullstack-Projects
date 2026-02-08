
package Bug.Tracking.System.Application.Bug.Tracking.Application.controller;

import Bug.Tracking.System.Application.Bug.Tracking.Application.entity.Sprint;
import Bug.Tracking.System.Application.Bug.Tracking.Application.entity.Bug;
import Bug.Tracking.System.Application.Bug.Tracking.Application.repository.Bugrepository;
import Bug.Tracking.System.Application.Bug.Tracking.Application.repository.SprintRepository;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/sprints")


@AllArgsConstructor
@CrossOrigin("*")
public class SprintController extends AbstractController {

    @Autowired
    private SprintRepository sprintRepository;

    @Autowired
    private Bugrepository bugRepository;

    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @GetMapping
    public List<Sprint> getAllSprints() {
        return sprintRepository.findAll();
    }
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    // Fetch bugs by sprint ID
    @GetMapping("/{sprintId}/bugs")
    public List<Bug> getBugsBySprint(@PathVariable Long sprintId) {
        Optional<Sprint> sprint = sprintRepository.findById(sprintId);

        if (sprint.isPresent()) {
            return bugRepository.findBySprintId(sprintId);
        } else {
            throw new RuntimeException("Sprint not found with ID: " + sprintId);
        }
    }
}
