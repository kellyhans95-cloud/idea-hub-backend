package rw.auca.idea_hub.controller;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import rw.auca.idea_hub.domain.Student;
import rw.auca.idea_hub.service.StudentService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestParam;
;




@RestController 
@RequestMapping ("/api/students")
public class StudentController {

    @Autowired
    private  StudentService studentService;

    @PostMapping(value = "/save", consumes = MediaType.APPLICATION_JSON_VALUE,
        produces =  MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<String> save(@RequestBody  Student student){
        String message = studentService.saveStudent(student);
        return  new ResponseEntity<>(message,HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Student>> getAll(){
        return  new ResponseEntity<>(studentService.getAllStudents(), HttpStatus.OK);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable  UUID id){
        Optional<Student> student =studentService.getStudentById(id);
        if(student.isPresent()){
            return  new ResponseEntity<>(student.get(),HttpStatus.OK);
        }else{
            return  new ResponseEntity<>("the student with that id does not exist", HttpStatus.NOT_FOUND);

        }


    }

    @PutMapping("update/{id}")
    public ResponseEntity<String> update(@PathVariable UUID id,@RequestBody  Student student){
        String message = studentService.updateStudent(id, student);
        if(message.equals("The student with that id does not exist")){
            return new ResponseEntity<>(message,HttpStatus.NOT_FOUND);
        }else{
            return  new ResponseEntity<>(message, HttpStatus.OK);
        }
    }

    @DeleteMapping ("/delete/{id}")
    public ResponseEntity<String> delte(@PathVariable  UUID id){
        String message = studentService.deleteStudent(id);
        if(message.equals("the student with that id does not exist")){
            return  new ResponseEntity<>(message,HttpStatus.NOT_FOUND);
        }else{
            return  new ResponseEntity<>(message,HttpStatus.OK);
        }
    }
    
    @GetMapping("/by-last-name")
    public ResponseEntity<?> getStudentsByLastName(@RequestParam String lastName){
        return new ResponseEntity<>(studentService.getStudentsByLastName(lastName),HttpStatus.OK);
        
    }
    
    
}
