package To_Do_Management_System.Todo_management_system.controller;

import To_Do_Management_System.Todo_management_system.dto.Tododto;
import To_Do_Management_System.Todo_management_system.service.Todoservice;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/bugs")
@AllArgsConstructor
public class Todocontroller {
    private Todoservice todoservice;
    //add todo restapi
    @PostMapping
    public ResponseEntity<Tododto>addToDo(@RequestBody Tododto tododto){
        Tododto savedToDo = todoservice.addToDo(tododto);
        return  new ResponseEntity<>(savedToDo, HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<Tododto>getToDo(@PathVariable("id") Long todoId){
        Tododto tododto = todoservice.getToDo(todoId);
        return new ResponseEntity<>(tododto, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Tododto>> getAllToDos(){
        List<Tododto> todos =todoservice.getAllTodos();
        //return  new ResponseEntity<>(todos,HttpStatus.OK);
        return  ResponseEntity.ok(todos);
    }

    @PutMapping("{id}")
    public  ResponseEntity<Tododto> updatedToDo(@PathVariable("id") Long todoid,@RequestBody Tododto tododto){
      Tododto updatedToDO =  todoservice.updateToDo(todoid,tododto);
        return ResponseEntity.ok(updatedToDO);
    }

    @DeleteMapping("{id}")
    public  ResponseEntity<String> deleteTODo(@PathVariable("id") Long todoid){
        todoservice.deleteToDo(todoid);
        return ResponseEntity.ok("Todo deleted sucessfully" );
    }
    //build complete rest api

        @PatchMapping("{id}/complete")
    public  ResponseEntity<Tododto> completeToDo(@PathVariable("id") Long todoid){
      Tododto updatedtodo =  todoservice.completeToDo(todoid);
        return ResponseEntity.ok(updatedtodo);
    }
    //build complete rest api

    @PatchMapping("{id}/incomplete")
    public  ResponseEntity<Tododto> inCompleteToDo(@PathVariable("id") Long todoid) {
        Tododto updatedtodo = todoservice.inCompleteToDo(todoid);
        return ResponseEntity.ok(updatedtodo);
    }

}
