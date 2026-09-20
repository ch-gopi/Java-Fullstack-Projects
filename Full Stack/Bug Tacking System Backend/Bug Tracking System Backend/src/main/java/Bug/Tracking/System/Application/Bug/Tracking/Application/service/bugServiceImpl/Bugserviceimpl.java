package Bug.Tracking.System.Application.Bug.Tracking.Application.service.bugServiceImpl;

import Bug.Tracking.System.Application.Bug.Tracking.Application.Enums.Severity;
import Bug.Tracking.System.Application.Bug.Tracking.Application.controller.Bugcontroller;
import Bug.Tracking.System.Application.Bug.Tracking.Application.dto.Bugdto;
import Bug.Tracking.System.Application.Bug.Tracking.Application.entity.Bug;

import Bug.Tracking.System.Application.Bug.Tracking.Application.entity.Sprint;
import Bug.Tracking.System.Application.Bug.Tracking.Application.entity.User;
import Bug.Tracking.System.Application.Bug.Tracking.Application.exception.ResourceNotFoundException;
import Bug.Tracking.System.Application.Bug.Tracking.Application.repository.BugCommentRepository;
import Bug.Tracking.System.Application.Bug.Tracking.Application.repository.Bugrepository;
import Bug.Tracking.System.Application.Bug.Tracking.Application.repository.SprintRepository;
import Bug.Tracking.System.Application.Bug.Tracking.Application.repository.UserRepository;
import Bug.Tracking.System.Application.Bug.Tracking.Application.service.BugInterfaceService;
import Bug.Tracking.System.Application.Bug.Tracking.Application.service.EmailInterfaceService;
import jakarta.mail.MessagingException;
import jakarta.persistence.criteria.Predicate;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;


@Service
@AllArgsConstructor
public class Bugserviceimpl implements BugInterfaceService {

    private final Bugrepository bugrepository;
    private final SprintRepository sprintRepository;
    private final ModelMapper modelMapper;
    private final UserRepository userRepository;
    private final EmailInterfaceService emailService;
    private final BugCommentRepository bugCommentRepository;

    private static final Logger logger = LoggerFactory.getLogger(Bugcontroller.class);

    @Override
    public Bugdto addBug(Bugdto bugdto) {
        Bug bug = modelMapper.map(bugdto, Bug.class);
        if (bugdto.getImagePaths() != null) {
            bug.setImagePaths(bugdto.getImagePaths());
        }

        Bug savedBug = bugrepository.save(bug);

        // Fetch assigned user details
        Optional<User> user = userRepository.findById(bugdto.getUserId());

        // Send Email Notification to Assigned User
        user.ifPresent(u -> {
            String emailBody = "A new bug has been assigned to you: " + bugdto.getTitle();
            try {
                emailService.sendEmail(u.getEmail(), "Bug Assigned", emailBody);
            } catch (MessagingException e) {
                logger.warn("Failed to send email to {}: {}", bugdto.getUserEmail(), e.getMessage());
                // throw new RuntimeException(e);
            }
        });
        return modelMapper.map(savedBug, Bugdto.class);
    }


    @Override
    public Bugdto getBug(Long id) {
        Bug bug = bugrepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("BUG NOT FOUND WITH ID " + id));
        return modelMapper.map(bug, Bugdto.class);
    }

    @Transactional(readOnly = true)
    @Override
    public Page<Bugdto> getAllBugs(Pageable pageable) {
        // Fetch all bugs and sort by 'fromDate'
        List<Bug> sortedBugs = bugrepository.findAll(Sort.by(Sort.Direction.DESC, "fromDate"));

        // Apply pagination after sorting
        int start = (int) pageable.getOffset();
        int end = Math.min(start + pageable.getPageSize(), sortedBugs.size());
        List<Bug> paginatedBugs = sortedBugs.subList(start, end);

        // Convert to DTO while ensuring imagePaths are loaded
        List<Bugdto> bugDtos = paginatedBugs.stream().map(bug -> {
            Bugdto dto = modelMapper.map(bug, Bugdto.class);
            dto.setImagePaths(bug.getImagePaths() != null ? new ArrayList<>(bug.getImagePaths()) : new ArrayList<>());
            return dto;
        }).collect(Collectors.toList());

        return new PageImpl<>(bugDtos, pageable, sortedBugs.size());
    }


    @Override
    public Bugdto updateBug(Bugdto bugdto, Long id) {
        Bug bug = bugrepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Bug not found: " + id));
        Sprint sprint = sprintRepository.findById(bugdto.getSprintId()).orElseThrow(() -> new RuntimeException("Sprint not found"));


        Optional<User> user = userRepository.findById(bugdto.getUserId());
        bug.setTitle(bugdto.getTitle());
        bug.setDescription(bugdto.getDescription());
        bug.setCompleted(bugdto.isCompleted());
        bug.setFromDate(bugdto.getFromDate());
        bug.setToDate(bugdto.getToDate());
        bug.setSprint(sprint);
        bug.setUser(user.orElse(null));
        bug.setSeverity(bugdto.getSeverity());

        Bug updatedBug = bugrepository.save(bug);

        // Notify the assigned user about the status change
        user.ifPresent(u -> {
            String status = bugdto.isCompleted() ? "Completed" : "Not Completed";
            String emailBody = "Your bug  has been updated to: " + status;

            try {
                emailService.sendEmail(u.getEmail(), "Bug Updated", emailBody);
                logger.info("Email sent successfully to assigned user: {}", u.getEmail());
            } catch (MessagingException e) {
                logger.warn("Failed to send email to {}: {}", u.getEmail(), e.getMessage());
            }
        });

        return modelMapper.map(updatedBug, Bugdto.class);
    }


    @Transactional
    @Override
    public void deleteBug(Long id) {
        bugCommentRepository.deleteByBugId(id);
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

    @Override
    @Transactional(readOnly = true)
    public List<Bugdto> getAllFilteredBugs(Map<String, String> filters) {
        Specification<Bug> spec = (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            //  Apply filters dynamically based on the frontend input
            if (filters.containsKey("searchTerm") && !filters.get("searchTerm").isEmpty()) {
                predicates.add(criteriaBuilder.like(root.get("title"), "%" + filters.get("searchTerm") + "%"));
            }
            if (filters.containsKey("selectedSeverity") && !filters.get("selectedSeverity").isEmpty()) {
                predicates.add(criteriaBuilder.equal(root.get("severity"), Severity.valueOf(filters.get("selectedSeverity").toUpperCase())));
            }
            if (filters.containsKey("selectedStatus") && !filters.get("selectedStatus").isEmpty()) {
                boolean isCompleted = filters.get("selectedStatus").equalsIgnoreCase("Completed");
                predicates.add(criteriaBuilder.equal(root.get("completed"), isCompleted));
            }
            if (filters.containsKey("selectedUser") && !filters.get("selectedUser").isEmpty()) {
                predicates.add(criteriaBuilder.equal(root.get("user").get("id"), Long.parseLong(filters.get("selectedUser"))));
            }
            if (filters.containsKey("selectedSprint") && !filters.get("selectedSprint").isEmpty()) {
                predicates.add(criteriaBuilder.equal(root.get("sprint").get("id"), Long.parseLong(filters.get("selectedSprint"))));
            }
            if (filters.containsKey("fromDate") && !filters.get("fromDate").isEmpty()) {
                predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("fromDate"), java.sql.Date.valueOf(filters.get("fromDate"))));
            }
            if (filters.containsKey("toDate") && !filters.get("toDate").isEmpty()) {
                predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("toDate"), java.sql.Date.valueOf(filters.get("toDate"))));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
        List<Bug> filteredBugs = bugrepository.findAll(spec);
        return filteredBugs.stream().map(bug -> modelMapper.map(bug, Bugdto.class)).collect(Collectors.toList());
    }
}
