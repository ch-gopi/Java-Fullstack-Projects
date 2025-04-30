package Bug.Tracking.System.Application.Bug.Tracking.Application.service.bugServiceImpl;

import Bug.Tracking.System.Application.Bug.Tracking.Application.dto.Bugdto;
import Bug.Tracking.System.Application.Bug.Tracking.Application.entity.Bug;
import Bug.Tracking.System.Application.Bug.Tracking.Application.exception.Resourcenotfoundexception;
import Bug.Tracking.System.Application.Bug.Tracking.Application.repository.Bugrepository;
import Bug.Tracking.System.Application.Bug.Tracking.Application.service.Bugservice;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class Bugserviceimpl implements Bugservice {

    private Bugrepository bugrepository;

    private ModelMapper modelMapper;
    @Override
    public Bugdto addBug(Bugdto bugdto) {
       /*convert todo dto entity into todo jpa entity
        Todo todo=new Todo();
        todo.setTitle(tododto.getTitle());
        todo.setDescription(tododto.getDescription());
        todo.setCompleted(tododto.isCompleted());
       */ Bug bug=modelMapper.map(bugdto,Bug.class);
        /* todo jpa entity */
        Bug savedBug = bugrepository.save(bug);
        /* convert add saved data entity object into todo object */
        Bugdto savedBugDto=modelMapper.map(savedBug,Bugdto.class);
/*        Tododto savedToDoDto = new Tododto();
        savedToDoDto.setId(savedToDo.getId());
        savedToDoDto.setTitle(savedToDo.getTitle());
        savedToDoDto.setDescription(savedToDo.getDescription());
        savedToDoDto.setCompleted(savedToDo.isCompleted());*/
        return savedBugDto;
    }


    @Override
    public Bugdto getBug(Long id) {
      Bug bug =  bugrepository.findById(id).orElseThrow(()-> new Resourcenotfoundexception("BUG NOT FOUND WITH ID"+ id));
      return  modelMapper.map(bug,Bugdto.class);
    }

    @Override
    public List<Bugdto> getAllBugs() {
        List<Bug> bugs = bugrepository.findAll();
        return bugs.stream().map((bug) -> modelMapper.map(bug,Bugdto.class)).collect(Collectors.toList());
    }

    @Override
    public Bugdto updateBug(Long id, Bugdto bugdto) {

       Bug bug= bugrepository.findById(id).orElseThrow(()-> new Resourcenotfoundexception("Bug not found:"+id));
        bug.setTitle(bugdto.getTitle());
        bug.setDescription(bugdto.getDescription());
        bug.setCompleted(bugdto.isCompleted());
       Bug updatedBug= bugrepository.save(bug);
       return modelMapper.map(updatedBug,Bugdto.class);
    }

    @Override
    public void deleteBug(Long id) {
      Bug bug= bugrepository.findById(id).orElseThrow(()->new Resourcenotfoundexception("Bug not found with id:"+id));
        bugrepository.deleteById(id);
    }

    @Override
    public Bugdto completeBug(Long id) {
       Bug bug= bugrepository.findById(id).orElseThrow(()-> new Resourcenotfoundexception("Bug not found with id:"+id));
        bug.setCompleted(Boolean.TRUE);
        Bug updatedBug= bugrepository.save(bug);
        return modelMapper.map(updatedBug,Bugdto.class);
    }

    @Override
    public Bugdto inCompleteBug(Long id) {
        Bug bug= bugrepository.findById(id).orElseThrow(()-> new Resourcenotfoundexception("Bug not found with id:"+id));
        bug.setCompleted(Boolean.FALSE);
        Bug updatedBug= bugrepository.save(bug);
        return modelMapper.map(updatedBug,Bugdto.class);
    }
}
