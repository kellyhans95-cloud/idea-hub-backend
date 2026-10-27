package rw.auca.idea_hub.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import rw.auca.idea_hub.domain.Workspace;
import java.util.UUID;
import java.util.Optional;


@Repository
public interface WorkspaceRepository extends JpaRepository<Workspace, UUID> {
    
    Optional<Workspace> findById(UUID id);
    Optional<Workspace> findByWorkspaceNumber(Integer workspaceNumber);
}
