package rw.auca.idea_hub.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import rw.auca.idea_hub.domain.Project;
import java.util.UUID;
import java.util.Optional;

@Repository 
public interface ProjectRepository extends JpaRepository<Project, UUID> {
    
    Optional<Project> findById(UUID id);
    Optional<Project> findByTitleAndOwnerName(String title, String ownerName);
    
}
