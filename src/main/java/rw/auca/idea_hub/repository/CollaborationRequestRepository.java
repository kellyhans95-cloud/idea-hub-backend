package rw.auca.idea_hub.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
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
    @Query ("""
        SELECT c.status,
        COUNT(c)
        FROM CollaborationRequest c
        GROUP BY c.status
        """)
    List<Object[]>countRequestsByStatus();

    @Modifying 
    @Query (" UPDATE CollaborationRequest c SET c.status = :rejected" +" WHERE c.project.id = :projectId AND c.requestDate = :date AND c.status <> :accepted")
    int cancelRequestsOfProjectOnDate(@Param ("projectId") UUID projectId,@Param ("date") LocalDate date,@Param ("rejected") RequestStatus rejected,@Param ("accepted") RequestStatus accepted);
    
}
