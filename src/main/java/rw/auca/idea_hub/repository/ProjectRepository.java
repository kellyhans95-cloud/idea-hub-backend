package rw.auca.idea_hub.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import rw.auca.idea_hub.domain.Project;
import java.util.UUID;
import java.util.List;
import java.util.Optional;

@Repository 
public interface ProjectRepository extends JpaRepository<Project, UUID> {
    
    Optional<Project> findById(UUID id);
    Optional<Project> findByTitleAndOwnerName(String title, String ownerName);

    @Query("""
        Select p 
        From Project p
        JOIN p.requiredSkills s
        WHERE LOWER(s.name) = LOWER(:name)

    """)
    List<Project> findProjectsBySkillName(@Param("name") String name);
    
}
