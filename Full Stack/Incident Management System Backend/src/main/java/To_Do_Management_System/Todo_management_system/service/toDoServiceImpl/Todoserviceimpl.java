package To_Do_Management_System.Todo_management_system.service.toDoServiceImpl;

import To_Do_Management_System.Todo_management_system.dto.Tododto;
import To_Do_Management_System.Todo_management_system.entity.Todo;
import To_Do_Management_System.Todo_management_system.exception.Resourcenotfoundexception;
import To_Do_Management_System.Todo_management_system.repository.Todorepository;
import To_Do_Management_System.Todo_management_system.service.Todoservice;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class Todoserviceimpl implements Todoservice {
    private Todorepository todorepository;
    private ModelMapper modelMapper;
    @Override
    public Tododto addToDo(Tododto tododto) {
/*        convert todo dto entity into todo jpa entity
        Todo todo=new Todo();
        todo.setTitle(tododto.getTitle());
        todo.setDescription(tododto.getDescription());
        todo.setCompleted(tododto.isCompleted());*/
        Todo todo=modelMapper.map(tododto,Todo.class);
        /* todo jpa entity */
        Todo savedToDo = todorepository.save(todo);
        /* convert add saved data entity object into todo object */
        Tododto savedToDoDto=modelMapper.map(savedToDo,Tododto.class);
/*        Tododto savedToDoDto = new Tododto();
        savedToDoDto.setId(savedToDo.getId());
        savedToDoDto.setTitle(savedToDo.getTitle());
        savedToDoDto.setDescription(savedToDo.getDescription());
        savedToDoDto.setCompleted(savedToDo.isCompleted());*/
        return savedToDoDto;
    }

    @Override
    public Tododto getToDo(Long id) {
      Todo todo =  todorepository.findById(id).orElseThrow(()-> new Resourcenotfoundexception("TODO NOT FOUND WITH ID"+ id));
      return  modelMapper.map(todo,Tododto.class);
    }

    @Override
    public List<Tododto> getAllTodos() {
        List<Todo> todos = todorepository.findAll();
        return todos.stream().map((todo) -> modelMapper.map(todo,Tododto.class)).collect(Collectors.toList());
    }

    @Override
    public Tododto updateToDo(Long id, Tododto tododto) {
       Todo todo= todorepository.findById(id).orElseThrow(()-> new Resourcenotfoundexception("Todo not found:"+id));
        todo.setTitle(tododto.getTitle());
        todo.setDescription(tododto.getDescription());
        todo.setCompleted(tododto.isCompleted());
       Todo updatedTODo= todorepository.save(todo);
       return modelMapper.map(updatedTODo,Tododto.class);
    }

    @Override
    public void deleteToDo(Long id) {
      Todo todo= todorepository.findById(id).orElseThrow(()->new Resourcenotfoundexception("Todo not found with id:"+id));
        todorepository.deleteById(id);
    }

    @Override
    public Tododto completeToDo(@PathVariable("{id}") Long id) {
       Todo todo= todorepository.findById(id).orElseThrow(()-> new Resourcenotfoundexception("Todo not found with id:"+id));
        todo.setCompleted(Boolean.TRUE);
        Todo updatedToDo= todorepository.save(todo);
        return modelMapper.map(updatedToDo,Tododto.class);
    }

    @Override
    public Tododto inCompleteToDo(Long id) {
        Todo todo= todorepository.findById(id).orElseThrow(()-> new Resourcenotfoundexception("Todo not found with id:"+id));
        todo.setCompleted(Boolean.FALSE);
        Todo updatedToDo= todorepository.save(todo);
        return modelMapper.map(updatedToDo,Tododto.class);
    }
}
