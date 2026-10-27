package rw.auca.idea_hub.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import rw.auca.idea_hub.domain.Skill;
import java.util.UUID;
import java.util.Optional;

@Repository 
public interface SkillRepository  extends JpaRepository<Skill, UUID> {
    Optional<Skill> findByName(String name);
    boolean existsByName (String name);

}
