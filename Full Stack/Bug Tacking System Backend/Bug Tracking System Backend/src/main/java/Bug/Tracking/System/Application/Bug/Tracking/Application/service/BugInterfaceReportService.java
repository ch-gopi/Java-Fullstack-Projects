package Bug.Tracking.System.Application.Bug.Tracking.Application.service;

import java.util.Map;

public interface BugInterfaceReportService {


    String generateCSVReport(Map<String, String> filters);
    String generatePDFReport(Map<String, String> filters); }
