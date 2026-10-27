package rw.auca.idea_hub.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import rw.auca.idea_hub.domain.CollaborationRequest;
import java.util.UUID;
import java.util.Optional;

@Repository 
public interface CollaborationRequestRepository extends JpaRepository<CollaborationRequest, UUID> {

    Optional<CollaborationRequest> findById(UUID id);
    
    
}
