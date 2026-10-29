package rw.auca.idea_hub.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import rw.auca.idea_hub.domain.CollaborationRequest;
import rw.auca.idea_hub.domain.Project;
import rw.auca.idea_hub.domain.RequestStatus;

import java.util.UUID;
import java.util.List;
import java.util.Optional;
import java.time.LocalDate;


@Repository 
public interface CollaborationRequestRepository extends JpaRepository<CollaborationRequest, UUID> {

    Optional<CollaborationRequest> findById(UUID id);
    List<CollaborationRequest> findByStatusOrderByRequestDateAsc(RequestStatus status);
    List<CollaborationRequest> findByRequestDateBetweenOrderByRequestDateAsc(LocalDate start , LocalDate end);
    boolean existsByProjectAndRequestDateAndStatusNot(Project project,LocalDate requesDate,RequestStatus status);
    
    
}
