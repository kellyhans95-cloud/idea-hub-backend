package rw.auca.idea_hub.controller;


import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import rw.auca.idea_hub.domain.Skill;
import rw.auca.idea_hub.service.SkillService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PutMapping;




@RestController 
@RequestMapping ("/api/skills")
public class SkillController {

    @Autowired 
    private SkillService skillService;

    @PostMapping(value = "/save", consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<String> saveSkill (@RequestBody Skill skill){
        String returnedMessage = skillService.saveSkill(skill);
        if ("Skill with that name already exist".equals(returnedMessage)) {
            return  new ResponseEntity<>(returnedMessage,HttpStatus.CONFLICT);           
        }
        return  new ResponseEntity<>(returnedMessage,HttpStatus.CREATED);
    }
    

    @GetMapping(value ="all")
    public ResponseEntity<?> getallSkills(){
        return  new ResponseEntity<>(skillService.getAllSkills(),HttpStatus.OK);
    }

    @GetMapping(value = "{id}")
    public ResponseEntity<?> getOneSkill(@PathVariable UUID id){
        Optional<Skill> skill= skillService.getSkillById(id);
        if(skill.isEmpty()){
            return new ResponseEntity<>("Thes skill with that id does not exist",HttpStatus.NOT_FOUND);

        }
        return new ResponseEntity<>(skill.get(),HttpStatus.OK);
    }

    @PutMapping(value = "update/{id}")
    public ResponseEntity<?> updateSkill(@PathVariable  UUID id, @RequestBody Skill skill){
        String returnedMessage = skillService.updateSkill(id, skill);
        if("The skill with that id does not exist".equals(returnedMessage)){
            return new ResponseEntity<>(returnedMessage,HttpStatus.NOT_FOUND);

        }
        return  new ResponseEntity<>(returnedMessage,HttpStatus.OK);
    }

    @DeleteMapping (value = "/delete/{id}")
    public  ResponseEntity<?> deleteSkill(@PathVariable UUID id){
        String returnedMessage = skillService.deleteSkill(id);
        if("The skill with that id does not exist".equals(returnedMessage)){
            return new ResponseEntity<>(returnedMessage,HttpStatus.NOT_FOUND);

        }
        return  new ResponseEntity<>(returnedMessage,HttpStatus.OK);
    }
   
    
    

   
}
