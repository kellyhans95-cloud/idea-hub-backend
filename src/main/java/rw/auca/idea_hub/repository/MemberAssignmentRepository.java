package rw.auca.idea_hub.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import rw.auca.idea_hub.domain.CollaborationRequest;
import rw.auca.idea_hub.domain.MemberAssignment;

public interface MemberAssignmentRepository extends JpaRepository<MemberAssignment, UUID>{
    
    boolean existsByCollaborationRequest(CollaborationRequest collaborationRequest);
    @Query ("""
        SELECT d 
        FROM MemberAssignment d
        WHERE d.collaborationRequest.student.regNumber = :regNumber
        ORDER BY d.assignedDate DESC
        """)
        List<MemberAssignment> findByStudentRegNumber(@Param("regNumber") String regNumber);
}
