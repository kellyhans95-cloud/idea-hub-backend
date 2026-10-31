package rw.auca.idea_hub.service;

import java.time.LocalDate;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rw.auca.idea_hub.domain.CollaborationRequest;
import rw.auca.idea_hub.domain.MemberAssignment;
import rw.auca.idea_hub.domain.RequestStatus;
import rw.auca.idea_hub.repository.CollaborationRequestRepository;
import rw.auca.idea_hub.repository.MemberAssignmentRepository;

@Service 
public class MemberAssignmentService {

    @Autowired 
    private MemberAssignmentRepository memberAssignmentRepository;
    @Autowired 
    private CollaborationRequestRepository collaborationRequestRepository;

    public String saveMemberAssignment(MemberAssignment assignment, UUID collaborationRequestId){
        Optional<CollaborationRequest> requestOpt = collaborationRequestRepository.findById(collaborationRequestId);
        if (requestOpt.isEmpty()) {
            return "The collaboration request with that id does not exist";
        }
        CollaborationRequest request = requestOpt.get();
        if (request.getStatus() != RequestStatus.ACCEPTED) {
            return  "Member assignments are only for ACCEPTED collaboration requests";
        }
        assignment.setAssignedDate(LocalDate.now());
        assignment.setCollaborationRequest(request);
        memberAssignmentRepository.save(assignment);
        return "Member assignment saved successfully";
    }

    public List<MemberAssignment> getAssignmentsByStudent(String regNumber){
        return  memberAssignmentRepository.findByStudentRegNumber(regNumber);
    }

    public String deleteMemberAssignment(UUID id){
        if (!memberAssignmentRepository.existsById(id)) {
            return "The member assignment with that id does not exist";
        }
        memberAssignmentRepository.deleteById(id);
        return "Member assignment deleted successfully";
    }
    
}
