package rw.auca.idea_hub.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import rw.auca.idea_hub.domain.Student;
import java.util.UUID;
import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, UUID> {
    
    Optional<Student> findById(UUID id);
    Optional<Student> findByRegNumber(String regNumber);
    List<Student> findByLastNameIgnoreCaseOrderByFirstNameAsc(String lastName);
}
