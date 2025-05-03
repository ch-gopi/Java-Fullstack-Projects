package Bug.Tracking.System.Application.Bug.Tracking.Application.service;

import Bug.Tracking.System.Application.Bug.Tracking.Application.dto.Bugdto;


import java.util.List;
import java.util.Map;

public interface Bugservice {
    Bugdto addBug(Bugdto bugdto);
    Bugdto getBug(Long id);
    List<Bugdto> getAllBugs();
    Bugdto  updateBug(Bugdto bugdto,Long id);
    void deleteBug(Long id);
    Bugdto completeBug(Long id);
    Bugdto inCompleteBug(Long id);
    Map<String, Object> generateAnalytics();
}
