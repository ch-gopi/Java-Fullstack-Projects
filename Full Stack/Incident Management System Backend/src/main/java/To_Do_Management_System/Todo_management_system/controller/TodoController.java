package To_Do_Management_System.Todo_management_system.controller;

import lombok.AllArgsConstructor;
import To_Do_Management_System.Todo_management_system.dto.Tododto;

import To_Do_Management_System.Todo_management_system.service.Todoservice;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("api/todos")
@AllArgsConstructor
public class TodoController {

    private Todoservice todoService;

    // Build Add Todo REST API

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<Tododto> addTodo(@RequestBody Tododto todoDto){
        Tododto savedTodo = todoService.addToDo(todoDto);

        return new ResponseEntity<>(savedTodo, HttpStatus.CREATED);
    }

    // Build Get Todo REST API
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @GetMapping("{id}")
    public ResponseEntity<Tododto> getTodo(@PathVariable("id") Long todoId){
        Tododto todoDto = todoService.getToDo(todoId);
        return new ResponseEntity<>(todoDto, HttpStatus.OK);
    }

    // Build Get All Todos REST API
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @GetMapping
    public ResponseEntity<List<Tododto>> getAllTodos(){
        List<Tododto> todos = todoService.getAllTodos();
        //return new ResponseEntity<>(todos, HttpStatus.OK);
        return ResponseEntity.ok(todos);
    }

    // Build Update Todo REST API
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("{id}")
    public ResponseEntity<Tododto> updateTodo(@RequestBody Tododto todoDto, @PathVariable("id") Long todoId){
        Tododto updatedTodo = todoService.updateToDo(todoId, todoDto);
        return ResponseEntity.ok(updatedTodo);
    }

    // Build Delete Todo REST API
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteTodo(@PathVariable("id") Long todoId){
        todoService.deleteToDo(todoId);
        return ResponseEntity.ok("Todo deleted successfully!.");
    }

    // Build Complete Todo REST API
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @PatchMapping("{id}/complete")
    public ResponseEntity<Tododto> completeTodo(@PathVariable("id") Long todoId){
        Tododto updatedTodo = todoService.completeToDo(todoId);
        return ResponseEntity.ok(updatedTodo);
    }

    // Build In Complete Todo REST API
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @PatchMapping("{id}/in-complete")
    public ResponseEntity<Tododto> inCompleteTodo(@PathVariable("id") Long todoId){
        Tododto updatedTodo = todoService.inCompleteToDo(todoId);
        return ResponseEntity.ok(updatedTodo);
    }

}
