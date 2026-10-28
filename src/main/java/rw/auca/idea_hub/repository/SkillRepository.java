package rw.auca.idea_hub.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import rw.auca.idea_hub.domain.Skill;
import java.util.UUID;
import java.util.List;
import java.util.Optional;

@Repository 
public interface SkillRepository  extends JpaRepository<Skill, UUID> {
    Optional<Skill> findByName(String name);
    boolean existsByName (String name);

    @Query ("""
        SELECT s
        FROM Skill s
        WHERE s.projects
        IS EMPTY
        """)
    List<Skill> findUnusedSkills();
}
