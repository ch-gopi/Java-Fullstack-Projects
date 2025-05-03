package Bug.Tracking.System.Application.Bug.Tracking.Application.service.bugServiceImpl;

import Bug.Tracking.System.Application.Bug.Tracking.Application.Enums.Severity;
import Bug.Tracking.System.Application.Bug.Tracking.Application.dto.Bugdto;
import Bug.Tracking.System.Application.Bug.Tracking.Application.entity.Bug;

import Bug.Tracking.System.Application.Bug.Tracking.Application.exception.ResourceNotFoundException;
import Bug.Tracking.System.Application.Bug.Tracking.Application.repository.Bugrepository;
import Bug.Tracking.System.Application.Bug.Tracking.Application.service.Bugservice;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Service
@AllArgsConstructor
public class Bugserviceimpl implements Bugservice {

    private final Bugrepository bugrepository;
    private final ModelMapper modelMapper;


    @Override
    public Bugdto addBug(Bugdto bugdto) {
        Bug bug = modelMapper.map(bugdto, Bug.class);
        Bug savedBug = bugrepository.save(bug);
        return modelMapper.map(savedBug, Bugdto.class);
    }

    @Override
    public Bugdto getBug(Long id) {
        Bug bug = bugrepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("BUG NOT FOUND WITH ID " + id));
        return modelMapper.map(bug, Bugdto.class);
    }

    @Override
    public List<Bugdto> getAllBugs() {
        List<Bug> bugs = bugrepository.findAll();
        return bugs.stream().map(bug -> modelMapper.map(bug, Bugdto.class)).collect(Collectors.toList());
    }

    @Override
    public Bugdto updateBug(Bugdto bugdto, Long id) {
        Bug bug = bugrepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Bug not found: " + id));

        bug.setTitle(bugdto.getTitle());
        bug.setDescription(bugdto.getDescription());
        bug.setCompleted(bugdto.isCompleted());
        bug.setFromDate(bugdto.getFromDate());
        bug.setToDate(bugdto.getToDate());
        bug.setSeverity(bugdto.getSeverity());

        Bug updatedBug = bugrepository.save(bug);
        return modelMapper.map(updatedBug, Bugdto.class);
    }

    @Override
    public void deleteBug(Long id) {
        Bug bug = bugrepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Bug not found with id: " + id));
        bugrepository.deleteById(id);
    }

    @Override
    public Bugdto completeBug(Long id) {
        Bug bug = bugrepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Bug not found with id: " + id));
        bug.setCompleted(Boolean.TRUE);
        Bug updatedBug = bugrepository.save(bug);
        return modelMapper.map(updatedBug, Bugdto.class);
    }

    @Override
    public Bugdto inCompleteBug(Long id) {
        Bug bug = bugrepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Bug not found with id: " + id));
        bug.setCompleted(Boolean.FALSE);
        Bug updatedBug = bugrepository.save(bug);
        return modelMapper.map(updatedBug, Bugdto.class);
    }
    @Override
    public Map<String, Object> generateAnalytics() {
        long totalBugs = bugrepository.count();
        long resolvedBugs = bugrepository.countByCompleted(true);
        long unresolvedBugs = totalBugs - resolvedBugs;
        long lowSeverity = bugrepository.countBySeverity(Severity.valueOf("LOW"));
        long mediumSeverity = bugrepository.countBySeverity(Severity.valueOf("MEDIUM"));
        long highSeverity = bugrepository.countBySeverity(Severity.valueOf("HIGH"));
        long criticalSeverity = bugrepository.countBySeverity(Severity.valueOf("CRITICAL"));

        Map<String, Object> analytics = new HashMap<>();
        analytics.put("totalBugs", totalBugs);
        analytics.put("resolvedBugs", resolvedBugs);
        analytics.put("unresolvedBugs", unresolvedBugs);
        analytics.put("lowSeverity", lowSeverity);
        analytics.put("mediumSeverity", mediumSeverity);
        analytics.put("highSeverity", highSeverity);
        analytics.put("criticalSeverity", criticalSeverity);

        return analytics;
    }
}
