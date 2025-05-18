package Bug.Tracking.System.Application.Bug.Tracking.Application.service.bugServiceImpl;

import Bug.Tracking.System.Application.Bug.Tracking.Application.dto.Bugdto;
import Bug.Tracking.System.Application.Bug.Tracking.Application.entity.Sprint;
import Bug.Tracking.System.Application.Bug.Tracking.Application.repository.SprintRepository;
import Bug.Tracking.System.Application.Bug.Tracking.Application.service.Bugservice;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.properties.TextAlignment;
import com.itextpdf.layout.properties.UnitValue;
import com.itextpdf.layout.properties.VerticalAlignment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

@Service
public class BugReportServiceImpl {

    private final Bugservice bugservice;

    @Autowired
    private SprintRepository sprintRepository;

    public BugReportServiceImpl(Bugservice bugservice) {
        this.bugservice = bugservice;
    }

    public String generateCSVReport(Map<String, String> filters) {
        String reportDirectory = System.getProperty("user.dir") + "/reports";
        File reportsDir = new File(reportDirectory);
        if (!reportsDir.exists()) {
            reportsDir.mkdir(); // ✅ Ensures "reports" directory exists
        }

        String filePath = reportDirectory + "/bugs_report.csv";

        List<Bugdto> bugList = (filters == null || filters.isEmpty())
                ? bugservice.getAllBugs(Pageable.unpaged()).getContent()
                : bugservice.getAllFilteredBugs(filters);
        List<Sprint> sprint = sprintRepository.findAll();
        Map<Long,String> hm=new HashMap<>();
        Set<Long> hs=new HashSet<>();
        for(Bugdto bug:bugList){
            hs.add(bug.getSprintId());
        }
        for(Sprint s:sprint){
            if( hs.contains(s.getId())){
                hm.put(s.getId(),s.getSprintName());
            }
        }

        try (FileWriter writer = new FileWriter(filePath)) {
            writer.append("Title,Description,Status,Severity,Assigned To,From Date,To Date,Sprint\n");

            for (Bugdto bug : bugList) {
                writer.append("\"").append(bug.getTitle()).append("\"").append(",");  // ✅ Wraps title in quotes
                writer.append("\"").append(bug.getDescription()).append("\"").append(",");  // ✅ Wraps description in quotes
                writer.append(bug.isCompleted() ? "Completed" : "Pending").append(",");
                writer.append(bug.getSeverity().toString()).append(",");
                writer.append(bug.getUserEmail()).append(",");
                writer.append(bug.getFromDate().toString()).append(",");
                writer.append(bug.getToDate().toString()).append(",");
                writer.append(bug.getSprintId() != null ?  hm.get(bug.getSprintId()) : "N/A").append("\n");
            }

            writer.flush();
            return filePath; // ✅ Returns actual file path
        } catch (IOException e) {
            return null; // ✅ Handle failure
        }
    }


    public String generatePDFReport(Map<String, String> filters) {
        String reportDirectory = System.getProperty("user.dir") + "/reports";
        File reportsDir = new File(reportDirectory);
        if (!reportsDir.exists()) {
            reportsDir.mkdir(); // ✅ Ensure reports directory exists
        }

        List<Bugdto> bugList = (filters == null || filters.isEmpty())
                ? bugservice.getAllBugs(Pageable.unpaged()).getContent()
                : bugservice.getAllFilteredBugs(filters);
        List<Sprint> sprint = sprintRepository.findAll();
        Map<Long,String> hm=new HashMap<>();
        Set<Long> hs=new HashSet<>();
        for(Bugdto bug:bugList){
            hs.add(bug.getSprintId());
        }
        for(Sprint s:sprint){
            if( hs.contains(s.getId())){
                hm.put(s.getId(),s.getSprintName());
            }
        }
        String filePath = reportDirectory + "/bugs_report.pdf";

        try {
            PdfWriter writer = new PdfWriter(filePath);
            PdfDocument pdfDoc = new PdfDocument(writer);
            Document document = new Document(pdfDoc);

            // ✅ Set custom margins (top: 20, right: 40, bottom: 20, left: 20)
            document.setMargins(20, 35, 20, 25);

            // ✅ Add Title
            document.add(new Paragraph("Bugs Tracking Report").setBold().setFontSize(16).setTextAlignment(TextAlignment.CENTER));

            // ✅ Create Table with Column Layout
            float[] columnWidths = { 3, 4, 2, 2, 3, 3, 3, 4}; // Adjusted for better fit
            Table table = new Table(UnitValue.createPercentArray(columnWidths)).useAllAvailableWidth();

            // ✅ Add Table Headers
            String[] headers = {"Title", "Description", "Status", "Severity", "Assigned To", "From Date", "To Date", "Sprint"};
            for (String header : headers) {
                table.addHeaderCell(new Cell().add(new Paragraph(header).setBold().setTextAlignment(TextAlignment.CENTER)));
            }

            // ✅ Add Data Rows with Wrapped Text
            for (Bugdto bug : bugList) {
                table.addCell(new Cell().add(new Paragraph(bug.getTitle()).setTextAlignment(TextAlignment.LEFT)));
                table.addCell(new Cell().add(new Paragraph(bug.getDescription()).setTextAlignment(TextAlignment.LEFT)));
                table.addCell(new Cell().add(new Paragraph(bug.isCompleted() ? "Completed" : "Pending").setTextAlignment(TextAlignment.CENTER)));
                table.addCell(new Cell().add(new Paragraph(bug.getSeverity().toString()).setTextAlignment(TextAlignment.CENTER)));
                table.addCell(new Cell().add(new Paragraph(bug.getUserEmail()).setTextAlignment(TextAlignment.LEFT)));
                table.addCell(new Cell().add(new Paragraph(bug.getFromDate().toString()).setTextAlignment(TextAlignment.CENTER)));
                table.addCell(new Cell().add(new Paragraph(bug.getToDate().toString()).setTextAlignment(TextAlignment.CENTER)));
                table.addCell(new Cell().add(new Paragraph(bug.getSprintId() != null ? hm.get(bug.getSprintId()) : "N/A").setTextAlignment(TextAlignment.CENTER)));
            }

            document.add(table);
            document.close();

            return filePath; // ✅ Returns actual file path
        } catch (Exception e) {
            return null; // ✅ Handle failure gracefully
        }
}}
