package rw.auca.idea_hub.controller;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import rw.auca.idea_hub.domain.CollaborationRequest;
import rw.auca.idea_hub.domain.RequestStatus;
import rw.auca.idea_hub.service.CollaborationRequestService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;




@RestController 
@RequestMapping ("/api/collaboration-requests")
public class CollaborationRequestController {

    @Autowired 
    private CollaborationRequestService collaborationRequestService;

    @PostMapping(value = "/save",consumes = MediaType.APPLICATION_JSON_VALUE,
    produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> saveCollaborationRequest(@RequestBody CollaborationRequest collaborationRequest){ 
       String returnedMessage = collaborationRequestService.saveCollaborationRequest(collaborationRequest);
       if ("Collaboration request date cannot be in the past".equals(returnedMessage)) {
        return new ResponseEntity<>(returnedMessage,HttpStatus.BAD_REQUEST);
        
       }else if ("Project already received a request on that date".equals(returnedMessage)) {
        return new ResponseEntity<>(returnedMessage,HttpStatus.CONFLICT);
       }
       return  new ResponseEntity<>(returnedMessage,HttpStatus.CREATED);
        }

    @GetMapping(value = "/all")
    public  ResponseEntity<?> getAllCollaborationRequests(){
        return new ResponseEntity<>(collaborationRequestService.getAllCollaborationRequests(),HttpStatus.OK);
    }

    @GetMapping (value ="/{id}")
    public ResponseEntity<?> getOneCollaborationRequest(@PathVariable UUID id){
        Optional<CollaborationRequest> request = collaborationRequestService.getCollaborationRequestById(id);
        if(request.isEmpty()){
            return  new ResponseEntity<>("The Collaboration request with that id does not exist",HttpStatus.NOT_FOUND);

        }
        return new ResponseEntity<>(request.get(),HttpStatus.OK);
    }

    @PutMapping(value = "/update/{id}")
    public ResponseEntity<?> updateCollaborationRequest(
        @PathVariable UUID id,
        @RequestBody  CollaborationRequest request){
            String returnedMessage = collaborationRequestService.updateCollaborationRequest(id, request);
            if("The Collaboration request with that id does not exist".equals(returnedMessage)){
                return new ResponseEntity<>(returnedMessage,HttpStatus.NOT_FOUND);
            }
        
        return new ResponseEntity<>(returnedMessage,HttpStatus.OK);
        }

    @PatchMapping (value = "/{id}/status")
    public ResponseEntity<?> updateCollaborationRequestStatus(@PathVariable UUID id,@RequestParam RequestStatus status){
        String returnedMessage = collaborationRequestService.updateCollaborationRequestStatus(id, status);
        if ("The collaboration request with that id does not exist".equals(returnedMessage)) {
            return new ResponseEntity<>(returnedMessage,HttpStatus.NOT_FOUND);            
        }else if (returnedMessage.startsWith("Cannot change status from")) {
            return new ResponseEntity<>(returnedMessage,HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(returnedMessage,HttpStatus.OK);
    }

    @DeleteMapping (value ="/delete/{id}")
    public ResponseEntity<?> deleteCollaborationRequest(@PathVariable UUID id){
        String returnedMessage = collaborationRequestService.deleteCollaborationRequestById(id);
        if ("The Collaboration request with that id does not exist".equals(returnedMessage)){
              return new ResponseEntity<>(returnedMessage,HttpStatus.NOT_FOUND);
        }else if ("That collaboration request hes member assignments,delete them first".equals(returnedMessage)) {
            return new ResponseEntity<>(returnedMessage,HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>(returnedMessage,HttpStatus.OK);
    }
    

    @GetMapping(value = "/by-status")
    public ResponseEntity<?> getCollaborationRequestsByStatus(@RequestParam RequestStatus status){
        return new ResponseEntity<>(collaborationRequestService.getCollaborationRequestsByStatus(status),HttpStatus.OK);
    }

    @GetMapping(value = "/between")
    public ResponseEntity<?> getCollaborationRequestsBetween(@RequestParam String start,@RequestParam String end){
        LocalDate starDate = LocalDate.parse(start);
        LocalDate enDate = LocalDate.parse(end);
        return new ResponseEntity<>(collaborationRequestService.getCollaborationRequestsBetween(starDate, enDate), HttpStatus.OK);
    }
    
    
    
    

    
    
    
    
}
