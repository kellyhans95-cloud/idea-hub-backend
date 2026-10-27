package rw.auca.idea_hub.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rw.auca.idea_hub.domain.Student;
import rw.auca.idea_hub.repository.StudentRepository;

@Service 
public class StudentService {

    @Autowired 
    private StudentRepository studentRepository;

    public String saveStudent(Student student) {
        Optional existing = studentRepository.findByRegNumber( student.getRegNumber());
        if(existing.isPresent()){
            return "Student with that regNumber alearedy exist";
        } 
        studentRepository.save(student);
        return "Student saved successfully!";
    }

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public Optional<Student> getStudentById(UUID id) {
        return studentRepository.findById(id);
    }

    public String updateStudent(UUID id, Student student) {
        Optional<Student> existing = studentRepository.findById(id);
        if (existing.isEmpty()) {
            return "Student with that ID does not exist.";
        }
        Student toUpdate = existing.get();
        if (student.getFirstName() !=null){
        toUpdate.setFirstName(student.getFirstName());
        }
        if (student.getLastName() !=null ){
        toUpdate.setLastName(student.getLastName());
        }
        if(student.getRegNumber() != null){
        toUpdate.setRegNumber(student.getRegNumber());
        }
        if(student.getEmail() != null){
        toUpdate.setEmail(student.getEmail());
        }
        if(student.getProgramme() != null){
        toUpdate.setProgramme(student.getProgramme());
        }
        studentRepository.save(toUpdate);
        return "Student updated successfully!";
       
    }

    public String deleteStudent(UUID id) {
        Optional<Student> existing = studentRepository.findById(id);
        if(existing.isPresent()){
            studentRepository.deleteById(id);
            return  " Student deleted successfully";
        }else{
            return "Student with that Id does not exist";
        }

        
    }
    
}
