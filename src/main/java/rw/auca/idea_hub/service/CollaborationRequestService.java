package rw.auca.idea_hub.service;

import java.util.UUID;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rw.auca.idea_hub.domain.CollaborationRequest;
import rw.auca.idea_hub.domain.RequestStatus;
import rw.auca.idea_hub.repository.CollaborationRequestRepository;

@Service 
public class CollaborationRequestService {

    @Autowired 
    private CollaborationRequestRepository collaborationRequestRepository;

    public String saveCollaborationRequest(CollaborationRequest collaborationRequest) {
        collaborationRequestRepository.save(collaborationRequest);
        return "Collaboration request saved successfully.";
   
    }

    public String deleteCollaborationRequestById(UUID id) {
        Optional<CollaborationRequest> existing = collaborationRequestRepository.findById(id);
        if (existing.isPresent()) {
            collaborationRequestRepository.deleteById(id);
            return "Collaboration request deleted successfully.";
        } else {
            return "Collaboration request with that ID does not exist.";
        }
    }

    public String updateCollaborationRequest(UUID id ,CollaborationRequest request){

        Optional<CollaborationRequest> existing = collaborationRequestRepository.findById(id);
        if(existing.isEmpty()){
            return "the collaboration request with that id does not exist";

        }
        CollaborationRequest toUpdate = existing.get();
        if(request.getRequestDate() != null){
            toUpdate.setRequestDate(request.getRequestDate());
        }
        if(request.getMessage() != null){
            toUpdate.setMessage(request.getMessage());
        }
        if(request.getProject() != null){
            toUpdate.setProject(request.getProject());
        }
        if(request.getStudent() !=null){
            toUpdate.setStudent(request.getStudent());
        }
        collaborationRequestRepository.save(toUpdate);
        return "Collaboration request updated successfully.";
        

    }

    public Optional<CollaborationRequest> getCollaborationRequestById(UUID id) {
        return collaborationRequestRepository.findById(id);
    }

    public List<CollaborationRequest> getAllCollaborationRequests() {
        return collaborationRequestRepository.findAll();
    }


    public List<CollaborationRequest> getCollaborationRequestsByStatus(RequestStatus status){
        return collaborationRequestRepository.findByStatusOrderByRequestDateAsc(status);
    }

    public List<CollaborationRequest> getCollaborationRequestsBetween(LocalDate start,LocalDate end){
        return  collaborationRequestRepository.findByRequestDateBetweenOrderByRequestDateAsc(start, end);
    }

    
}
