package rw.auca.idea_hub.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rw.auca.idea_hub.domain.Project;
import rw.auca.idea_hub.repository.ProjectRepository;

@Service 
public class ProjectService {
    
    @Autowired 
    private ProjectRepository projectRepository;

    public String saveProject(Project project) {
        Optional<Project> existing = projectRepository.findByTitleAndOwnerName(project.getTitle(), project.getOwnerName());
        if (existing.isPresent()) {
            return "Project with that title and owner name already exists.";
        }
        projectRepository.save(project);
        return "Project saved successfully!";
    }

    public List<Project> getAllProjects() {
        return projectRepository.findAll();
    }

    public Optional<Project> getProjectById(java.util.UUID id) {
        return projectRepository.findById(id);
    }

    public String updateProject(java.util.UUID id, Project project) {
        Optional<Project> existing = projectRepository.findById(id);
        if (existing.isEmpty()) {
            return "Project with that ID does not exist.";
        }
        Project toUpdate = existing.get();
        if(project.getTitle() != null ) {
            toUpdate.setTitle(project.getTitle());
        }
        if(project.getOwnerName() != null ) {
            toUpdate.setOwnerName(project.getOwnerName());
        }
        if(project.getDescription() != null ) {
            toUpdate.setDescription(project.getDescription());
        }
        if(project.getWorkspace() != null ) {
            toUpdate.setWorkspace(project.getWorkspace());
        }
        projectRepository.save(toUpdate);
        return "Project updated successfully!";

    }

    public String deleteProject(UUID id) {
        Optional<Project> existing = projectRepository.findById(id);
        if (existing.isPresent()) {
            projectRepository.deleteById(id);
            return "Project deleted successfully!";
        } else {
            return "Project with that ID does not exist.";
        }
    }

    public List<Project> getProjectsByskill(String name){
        return projectRepository.findProjectsBySkillName(name);
    }
}
