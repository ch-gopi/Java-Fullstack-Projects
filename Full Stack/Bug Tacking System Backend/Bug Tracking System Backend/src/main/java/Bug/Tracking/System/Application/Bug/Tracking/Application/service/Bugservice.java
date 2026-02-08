package Bug.Tracking.System.Application.Bug.Tracking.Application.service;

import Bug.Tracking.System.Application.Bug.Tracking.Application.dto.Bugdto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


import java.util.List;
import java.util.Map;

public interface Bugservice {
    Bugdto addBug(Bugdto bugdto);
    Bugdto getBug(Long id);
    Page<Bugdto> getAllBugs(Pageable pageable);
    Bugdto  updateBug(Bugdto bugdto,Long id);
    void deleteBug(Long id);
    Bugdto completeBug(Long id);
    Bugdto inCompleteBug(Long id);
    Map<String, Object> generateAnalytics();
    List<Bugdto> getAllFilteredBugs(Map<String, String> filters);
}
