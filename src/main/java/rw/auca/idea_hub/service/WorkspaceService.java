package rw.auca.idea_hub.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import rw.auca.idea_hub.domain.Workspace;
import rw.auca.idea_hub.repository.WorkspaceRepository;

@Service 
public class WorkspaceService {

    
    @Autowired 
    private WorkspaceRepository workspaceRepository;

    

    public String saveWorkspace(Workspace workspace) {
        Optional<Workspace> existing = workspaceRepository.findByWorkspaceNumber(workspace.getWorkspaceNumber());
        if(existing.isPresent()){
            return "Worspace with that number aleardy exist";
        } 
        workspaceRepository.save(workspace);
        return "Workspace saved successfully!";
    }

    public List<Workspace> getAllWorkspaces() {
        return workspaceRepository.findAll();
    }

    public Optional<Workspace> getWorkspaceById(UUID id) {
        return workspaceRepository.findById(id);
    }
    

    public String updateWorkspace(UUID id, Workspace workspace) {
        Optional<Workspace> existing = workspaceRepository.findById(id);
        if (existing.isEmpty()) {
            return "Workspace with that ID does not exist.";
        }
        Workspace toUpdate = existing.get();
       if(workspace.getWorkspaceNumber() != null){
        toUpdate.setWorkspaceNumber(workspace.getWorkspaceNumber());
       }
       if(workspace.getName() !=null){
        toUpdate.setName(workspace.getName());
       }
       workspaceRepository.save(toUpdate);
       return  "Workspace Updated successfully";
    }

    public String deleteWorkspace(UUID id) {
      Optional<Workspace> existing = workspaceRepository.findById(id);
      if(existing.isPresent()){
        workspaceRepository.deleteById(id);
        return "Worksppace deleted successefully";
      }else{
        return "Workspace with that id does not exist";
      }
    }

    public List<Workspace> getFreeWorkspaces(){
        return  workspaceRepository.findFreeWorkspaces();
    }
}
