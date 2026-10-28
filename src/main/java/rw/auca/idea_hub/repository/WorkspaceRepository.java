package rw.auca.idea_hub.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import rw.auca.idea_hub.domain.Workspace;
import java.util.UUID;
import java.util.List;
import java.util.Optional;


@Repository
public interface WorkspaceRepository extends JpaRepository<Workspace, UUID> {
    
    Optional<Workspace> findById(UUID id);
    Optional<Workspace> findByWorkspaceNumber(Integer workspaceNumber);

    @Query ("""
        SELECT l 
        FROM Workspace l
        WHERE NOT EXISTS(SELECT p FROM Project p WHERE p.workspace = l)
        ORDER BY l.workspaceNumber ASC

    """)
    List<Workspace> findFreeWorkspaces();
}
