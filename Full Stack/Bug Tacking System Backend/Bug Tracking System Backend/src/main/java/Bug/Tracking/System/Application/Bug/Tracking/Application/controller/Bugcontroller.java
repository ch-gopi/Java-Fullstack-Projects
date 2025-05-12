package Bug.Tracking.System.Application.Bug.Tracking.Application.controller;

import Bug.Tracking.System.Application.Bug.Tracking.Application.dto.Bugdto;
import Bug.Tracking.System.Application.Bug.Tracking.Application.service.Bugservice;
import Bug.Tracking.System.Application.Bug.Tracking.Application.service.bugServiceImpl.EmailServiceImpl;
import jakarta.mail.MessagingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


import java.util.List;
import java.util.Map;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/bugs")
@AllArgsConstructor
public class Bugcontroller {

    private static final Logger logger = LoggerFactory.getLogger(Bugcontroller.class);
    private Bugservice bugservice;
    private final EmailServiceImpl emailService;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<Bugdto> addBug(@RequestBody Bugdto bugdto) throws MessagingException {
        Bugdto savedBug = bugservice.addBug(bugdto);

        // Send Email Notification to Assigned User
        String emailBody = "A new bug has been assigned to you: " + bugdto.getTitle();
        try {
            emailService.sendEmail(bugdto.getUserEmail(), "Bug Assigned", emailBody);
        } catch (MessagingException e) {
            logger.warn("Failed to send email to {}: {}", bugdto.getUserEmail(), e.getMessage());
        }  catch (Exception e) {
            logger.warn("Failed to send email to {}: {}", bugdto.getUserEmail(), e.getMessage());
        }

        return new ResponseEntity<>(savedBug, HttpStatus.CREATED);
    }

    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @GetMapping("{id}")
    public ResponseEntity<Bugdto>getBug(@PathVariable("id") Long bugId){
        Bugdto bugdto = bugservice.getBug(bugId);
        return new ResponseEntity<>(bugdto, HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @GetMapping
    public ResponseEntity<Page<Bugdto>> getAllBugs(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "8") int size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Bugdto> bugs = bugservice.getAllBugs(pageable); // Pass pagination info

        return ResponseEntity.ok(bugs);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("{id}")
    public ResponseEntity<Bugdto> updatedBug(@PathVariable("id") Long bugid, @RequestBody Bugdto bugdto) throws MessagingException {
        Bugdto updatedBug = bugservice.updateBug(bugdto, bugid);

        // Send Email Notification on Status Update
        String emailBody = "Bug status updated: " + (bugdto.isCompleted() ? "Completed" : "Not Completed");
        try {
            emailService.sendEmail(bugdto.getUserEmail(), "Bug Status Updated", emailBody);
        } catch (MessagingException e) {
            logger.warn("Failed to send email to {}: {}", bugdto.getUserEmail(), e.getMessage());
        } catch (Exception e) {
            logger.warn("Failed to send email to {}: {}", bugdto.getUserEmail(), e.getMessage());
        }
        return ResponseEntity.ok(updatedBug);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteBug(@PathVariable("id") Long bugid) {
        bugservice.deleteBug(bugid);
        return ResponseEntity.ok("Bug deleted sucessfully");
    }

    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @PatchMapping("{id}/complete")
    public ResponseEntity<Bugdto> completeBug(@PathVariable("id") Long bugid) {
        Bugdto updatedbug = bugservice.completeBug(bugid);
        return ResponseEntity.ok(updatedbug);
    }

    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @PatchMapping("{id}/incomplete")
    public ResponseEntity<Bugdto> inCompleteBug(@PathVariable("id") Long bugid) {
        Bugdto updatedbug = bugservice.inCompleteBug(bugid);
        return ResponseEntity.ok(updatedbug);
    }

    // @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @GetMapping("/analytics")
    public ResponseEntity<Map<String, Object>> getBugAnalytics() {
        Map<String, Object> analytics = bugservice.generateAnalytics();
        return ResponseEntity.ok(analytics);
    }

}
