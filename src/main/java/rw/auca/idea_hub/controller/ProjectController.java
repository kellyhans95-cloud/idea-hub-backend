package rw.auca.idea_hub.controller;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import rw.auca.idea_hub.domain.Project;
import rw.auca.idea_hub.service.ProjectService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PutMapping;





@RestController 
@RequestMapping ("/api/projects")
public class ProjectController {
    
    @Autowired 
    private ProjectService projectService;

    @PostMapping(value = "/save", consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE
     )
    public ResponseEntity<?> saveProject(@RequestBody  Project project){
        String returnedMessage = projectService.saveProject(project);
        return  new  ResponseEntity<>(returnedMessage,HttpStatus.CREATED);

    }
    
    @GetMapping(value = "/all")
    public ResponseEntity<?> getAllProject(){
        return new ResponseEntity<>(projectService.getAllProjects(), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> getOneProject(@PathVariable UUID id){
        Optional<Project> project = projectService.getProjectById(id);
        if (project.isEmpty()){
            return new ResponseEntity<>("The project with that id does not exist", HttpStatus.NOT_FOUND);

        }
        return  new ResponseEntity<>(project.get(),HttpStatus.OK);

    }
    
    @PutMapping(value = "/update/{id}")
    public ResponseEntity<?> updateProject(@PathVariable  UUID id ,@RequestBody Project project ){
        String returnedMessage = projectService.updateProject(id, project);
        if ("The project with that id does not exist".equals(returnedMessage)){
            return new ResponseEntity<>(returnedMessage,HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(returnedMessage,HttpStatus.OK);
    }

    @DeleteMapping (value = "/dalete/{id}")
    public ResponseEntity<?> deleteProject(@PathVariable UUID id){
        String returnedMessage = projectService.deleteProject(id);
        if ("The project with that id does not exit".equals(returnedMessage)){
            return  new ResponseEntity<>(returnedMessage,HttpStatus.NOT_FOUND);

        }
        return  new ResponseEntity<>(returnedMessage,HttpStatus.OK);
    }

    @GetMapping(value = "/by-skill")
    public ResponseEntity<?> getProjectBySkill(@RequestParam String name){
        return new ResponseEntity<>(projectService.getProjectsByskill(name),HttpStatus.OK);
    }
    
    
}
