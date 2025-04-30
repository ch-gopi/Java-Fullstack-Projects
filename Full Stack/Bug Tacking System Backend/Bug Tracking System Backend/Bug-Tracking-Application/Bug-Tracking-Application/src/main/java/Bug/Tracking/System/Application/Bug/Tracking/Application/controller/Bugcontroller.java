package Bug.Tracking.System.Application.Bug.Tracking.Application.controller;

import Bug.Tracking.System.Application.Bug.Tracking.Application.dto.Bugdto;
import Bug.Tracking.System.Application.Bug.Tracking.Application.service.Bugservice;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


import java.util.List;
@CrossOrigin("*")
@RestController
@RequestMapping("/api/bugs")
@AllArgsConstructor
public class Bugcontroller {


    private Bugservice bugservice;

    @PreAuthorize("hasRole('ADMIN')")
    //add bug restapi
    @PostMapping
    public ResponseEntity<Bugdto> addBug(@RequestBody Bugdto bugdto){
        Bugdto savedBug = bugservice.addBug(bugdto);
        return  new ResponseEntity<>(savedBug, HttpStatus.CREATED);
    }
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @GetMapping("{id}")
    public ResponseEntity<Bugdto>getBug(@PathVariable("id") Long bugId){
        Bugdto bugdto = bugservice.getBug(bugId);
        return new ResponseEntity<>(bugdto, HttpStatus.OK);
    }
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @GetMapping
    public ResponseEntity<List<Bugdto>> getAllBugs(){
        List<Bugdto> bugs =bugservice.getAllBugs();
        //return  new ResponseEntity<>(bugs,HttpStatus.OK);
        return  ResponseEntity.ok(bugs);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("{id}")
    public  ResponseEntity<Bugdto> updatedBug(@PathVariable("id") Long bugid,@RequestBody Bugdto bugdto){
        Bugdto updatedBug =  bugservice.updateBug(bugid,bugdto);
        return ResponseEntity.ok(updatedBug);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("{id}")
    public  ResponseEntity<String> deleteBug(@PathVariable("id") Long bugid){
        bugservice.deleteBug(bugid);
        return ResponseEntity.ok("Bug deleted sucessfully" );
    }
    //build complete rest api
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @PatchMapping("{id}/complete")
    public  ResponseEntity<Bugdto> completeBug(@PathVariable("id") Long bugid){
        Bugdto updatedbug =  bugservice.completeBug(bugid);
        return ResponseEntity.ok(updatedbug);
    }
    //build complete rest api
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @PatchMapping("{id}/incomplete")
    public  ResponseEntity<Bugdto> inCompleteBug(@PathVariable("id") Long bugid) {
        Bugdto updatedbug = bugservice.inCompleteBug(bugid);
        return ResponseEntity.ok(updatedbug);
    }

}
