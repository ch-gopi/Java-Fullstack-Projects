package Bug.Tracking.System.Application.Bug.Tracking.Application.service;

import Bug.Tracking.System.Application.Bug.Tracking.Application.dto.Bugdto;


import java.util.List;

public interface Bugservice {
    Bugdto addBug(Bugdto bugdto);
    Bugdto getBug(Long id);
    List<Bugdto> getAllBugs();
    Bugdto  updateBug(Long id,Bugdto bugdto);
    void deleteBug(Long id);
    Bugdto completeBug(Long id);
    Bugdto inCompleteBug(Long id);
}
