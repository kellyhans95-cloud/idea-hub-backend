package rw.auca.idea_hub.controller;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.http.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import rw.auca.idea_hub.domain.Workspace;
import rw.auca.idea_hub.service.WorkspaceService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;




@RestController 
@RequestMapping("/api/workspaces")
public class WorkspaceController {

    @Autowired 
    private WorkspaceService workspaceService;

    @PostMapping(value ="/save", consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
        public ResponseEntity<?> saveWorkspace(@RequestBody Workspace workspace){
            String returnedMessage = workspaceService.saveWorkspace(workspace);
            return new ResponseEntity<>(returnedMessage,HttpStatus.CREATED);
        }


    @GetMapping("/all")
    public ResponseEntity<List<Workspace>> getAll(){
        return new ResponseEntity<>(workspaceService.getAllWorkspaces(), HttpStatus.OK);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable UUID id){
        Optional<Workspace> workspace = workspaceService.getWorkspaceById(id);
        if (workspace.isPresent()){
            return  new ResponseEntity<>(workspace.get(),HttpStatus.OK);
        }else{
            return new ResponseEntity<>("The workspace with that id does not exist",HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("update/{id}")
    public ResponseEntity<String> update(@PathVariable  UUID id,@RequestBody Workspace workspace){
        String message = workspaceService.updateWorkspace(id, workspace);
        if(message.equals("The workspace with that id does not exist")){
            return new  ResponseEntity<>(message,HttpStatus.NOT_FOUND);
        }else{
            return  new ResponseEntity<>(message,HttpStatus.OK);
        }
    }

    @DeleteMapping ("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable UUID id){
        String message = workspaceService.deleteWorkspace(id);
        if(message.equals("the workspace with that id does not exist")){
            return new ResponseEntity<>(message,HttpStatus.NOT_FOUND);
        }else{
            return  new ResponseEntity<>(message,HttpStatus.OK);
        }
    }
    
    
}
