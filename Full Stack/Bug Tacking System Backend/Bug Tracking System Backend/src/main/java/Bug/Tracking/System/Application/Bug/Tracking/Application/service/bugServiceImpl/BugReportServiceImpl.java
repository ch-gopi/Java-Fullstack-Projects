package Bug.Tracking.System.Application.Bug.Tracking.Application.service.bugServiceImpl;

import Bug.Tracking.System.Application.Bug.Tracking.Application.dto.Bugdto;
import Bug.Tracking.System.Application.Bug.Tracking.Application.entity.Sprint;
import Bug.Tracking.System.Application.Bug.Tracking.Application.repository.SprintRepository;
import Bug.Tracking.System.Application.Bug.Tracking.Application.service.BugInterfaceReportService;
import Bug.Tracking.System.Application.Bug.Tracking.Application.service.BugInterfaceService;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.properties.TextAlignment;
import com.itextpdf.layout.properties.UnitValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class BugReportServiceImpl implements BugInterfaceReportService {

    private final BugInterfaceService bugservice;

    @Autowired
    private SprintRepository sprintRepository;

    public BugReportServiceImpl(BugInterfaceService bugservice) {
        this.bugservice = bugservice;
    }

    public String generateCSVReport(Map<String, String> filters) {
        String reportDirectory = System.getProperty("user.dir") + "/reports";
        File reportsDir = new File(reportDirectory);
        if (!reportsDir.exists()) {
            reportsDir.mkdir();
        }

        String filePath = reportDirectory + "/bugs_report.csv";

        List<Bugdto> bugList = (filters == null || filters.isEmpty())
                ? bugservice.getAllBugs(PageRequest.of(0, Integer.MAX_VALUE, Sort.by(Sort.Direction.DESC, "fromDate"))).getContent()
                : bugservice.getAllFilteredBugs(filters).stream()
                .sorted(Comparator.comparing(Bugdto::getFromDate).reversed())
                .collect(Collectors.toList());

        List<Sprint> sprint = sprintRepository.findAll();
        Map<Long, String> hm = new HashMap<>();
        Set<Long> hs = new HashSet<>();
        for (Bugdto bug : bugList) {
            hs.add(bug.getSprintId());
        }
        for (Sprint s : sprint) {
            if (hs.contains(s.getId())) {
                hm.put(s.getId(), s.getSprintName());
            }
        }

        try (FileWriter writer = new FileWriter(filePath)) {
            writer.append("Title,Description,Status,Severity,Assigned To,From Date,To Date,Sprint\n");

            for (Bugdto bug : bugList) {
                writer.append("\"").append(bug.getTitle()).append("\"").append(",");
                writer.append("\"").append(bug.getDescription()).append("\"").append(",");
                writer.append(bug.isCompleted() ? "Completed" : "Pending").append(",");
                writer.append(bug.getSeverity().toString()).append(",");
                writer.append(bug.getUserEmail()).append(",");
                writer.append(bug.getFromDate().toString()).append(",");
                writer.append(bug.getToDate().toString()).append(",");
                writer.append(bug.getSprintId() != null ? hm.get(bug.getSprintId()) : "N/A").append("\n");
            }

            writer.flush();
            return filePath;
        } catch (IOException e) {
            throw new RuntimeException("Error while generating PDF report", e);
        }
    }


    public String generatePDFReport(Map<String, String> filters) {
        String reportDirectory = System.getProperty("user.dir") + "/reports";
        File reportsDir = new File(reportDirectory);
        if (!reportsDir.exists()) {
            reportsDir.mkdir();
        }

        List<Bugdto> bugList = (filters == null || filters.isEmpty())
                ? bugservice.getAllBugs(PageRequest.of(0, Integer.MAX_VALUE, Sort.by(Sort.Direction.DESC, "fromDate"))).getContent()
                : bugservice.getAllFilteredBugs(filters).stream()
                .sorted(Comparator.comparing(Bugdto::getFromDate).reversed())
                .collect(Collectors.toList());

        List<Sprint> sprint = sprintRepository.findAll();
        Map<Long, String> hm = new HashMap<>();
        Set<Long> hs = new HashSet<>();
        for (Bugdto bug : bugList) {
            hs.add(bug.getSprintId());
        }
        for (Sprint s : sprint) {
            if (hs.contains(s.getId())) {
                hm.put(s.getId(), s.getSprintName());
            }
        }
        String filePath = reportDirectory + "/bugs_report.pdf";

        try {
            PdfWriter writer = new PdfWriter(filePath);
            PdfDocument pdfDoc = new PdfDocument(writer);
            Document document = new Document(pdfDoc);

            document.setMargins(20, 35, 20, 25);


            document.add(new Paragraph("Bugs Tracking Report").setBold().setFontSize(16).setTextAlignment(TextAlignment.CENTER));

            float[] columnWidths = {3, 4, 2, 2, 3, 3, 3, 4};
            Table table = new Table(UnitValue.createPercentArray(columnWidths)).useAllAvailableWidth();

            String[] headers = {"Title", "Description", "Status", "Severity", "Assigned To", "From Date", "To Date", "Sprint"};
            for (String header : headers) {
                table.addHeaderCell(new Cell().add(new Paragraph(header).setBold().setTextAlignment(TextAlignment.CENTER)));
            }

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

            return filePath;
        } catch (Exception e) {
            throw new RuntimeException("Error while generating CSV report", e);
        }
    }
}
