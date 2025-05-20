package Bug.Tracking.System.Application.Bug.Tracking.Application.controller;

import Bug.Tracking.System.Application.Bug.Tracking.Application.service.bugServiceImpl.BugReportServiceImpl;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/reports")
public class BugReportController {

    private final BugReportServiceImpl bugReportService;
    private static final Logger logger = LoggerFactory.getLogger(BugReportController.class);

    public BugReportController(BugReportServiceImpl bugReportService) {
        this.bugReportService = bugReportService;
    }

   // @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @GetMapping("/export/csv")
    public ResponseEntity<Resource> viewCSV(@RequestParam Map<String, String> filters) {
        String filePath = bugReportService.generateCSVReport(filters);
        File file = new File(filePath);

        if (!file.exists()) {
            logger.error("CSV file not found: " + filePath);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        Resource resource = new FileSystemResource(file);
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType("text/csv"))
                .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"bugs_report.csv\"") 
                .body(resource);
    }

   // @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @GetMapping("/export/pdf")
    public ResponseEntity<Resource> viewPDF(@RequestParam Map<String, String> filters) {
        String filePath = bugReportService.generatePDFReport(filters);
        File file = new File(filePath);

        if (!file.exists()) {
            logger.error("PDF file not found: " + filePath);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        Resource resource = new FileSystemResource(file);
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_PDF)
                .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"bugs_report.pdf\"") 
                .body(resource);
    }
}
