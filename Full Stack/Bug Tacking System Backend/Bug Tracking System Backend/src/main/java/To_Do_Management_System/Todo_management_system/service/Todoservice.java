package To_Do_Management_System.Todo_management_system.service;

import To_Do_Management_System.Todo_management_system.dto.Tododto;

import java.util.List;

public interface Todoservice {
    Tododto addToDo(Tododto tododto);
    Tododto getToDo(Long id);
    List<Tododto> getAllTodos();
    Tododto  updateToDo(Long id,Tododto tododto);
    void deleteToDo(Long id);
    Tododto completeToDo(Long id);
    Tododto inCompleteToDo(Long id);
}
