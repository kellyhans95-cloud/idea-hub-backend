package rw.auca.idea_hub.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import rw.auca.idea_hub.domain.MemberAssignment;
import rw.auca.idea_hub.service.MemberAssignmentService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;



@RestController 
@RequestMapping ("/api/member-assignments")
public class MemberAssignmentController {
    
    @Autowired 
    private MemberAssignmentService memberAssignmentService;


    @PostMapping(value = "/save", consumes = MediaType.APPLICATION_JSON_VALUE,
        produces =  MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<?> saveMemberAssignment(@RequestBody MemberAssignment assignment,@RequestParam UUID collaborationRequestId){
        String returnedMessage = memberAssignmentService.saveMemberAssignment(assignment, collaborationRequestId);
        if ("The collaboration request with that id does not exist".equals(returnedMessage)) {
            return new ResponseEntity<>(returnedMessage,HttpStatus.NOT_FOUND);

        }else if ("Member assignments are only for ACCEPTED collaboration request".equals(returnedMessage)) {
            return new ResponseEntity<>(returnedMessage,HttpStatus.BAD_REQUEST);
        }
        return  new ResponseEntity<>(returnedMessage,HttpStatus.OK);
    }

    @GetMapping(value = "/student/{regNumber}")
    public ResponseEntity<?> getAssignmentsByStudent(@PathVariable String regNumber){
        return new ResponseEntity<>(memberAssignmentService.getAssignmentsByStudent(regNumber),HttpStatus.OK);
    }

    @DeleteMapping (value = "/delete/{id}")
    public ResponseEntity<?> deleteMemberAssignment(@PathVariable UUID id){
        String returnedMessage = memberAssignmentService.deleteMemberAssignment(id);
        if ("The member assignment with that id does not exist".equals(returnedMessage)) {
            return new ResponseEntity<>(returnedMessage,HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(returnedMessage,HttpStatus.OK);
    }
    
}
