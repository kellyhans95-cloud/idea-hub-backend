package rw.auca.idea_hub.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rw.auca.idea_hub.domain.Project;
import rw.auca.idea_hub.domain.Skill;
import rw.auca.idea_hub.domain.Workspace;
import rw.auca.idea_hub.repository.ProjectRepository;
import rw.auca.idea_hub.repository.SkillRepository;
import rw.auca.idea_hub.repository.WorkspaceRepository;

@Service 
public class ProjectService {
    
    @Autowired 
    private ProjectRepository projectRepository;
    @Autowired 
    private SkillRepository skillRepository;
    @Autowired 
    private WorkspaceRepository workspaceRepository;

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

    public String addSkillToProject(UUID projectId,UUID skillId){
        Optional<Project> projectOpt = projectRepository.findById(projectId);
        if (projectOpt.isEmpty()) {
            return "The project with that does not exist";
        }
        Optional<Skill> skillopt = skillRepository.findById(skillId);
        if (skillopt.isEmpty()) {
            return "That skill does not exist";
        }
        Project project = projectOpt.get();
        if (project.getRequiredSkills().contains(skillopt.get())) {
            return "The project already has that skill";
        }
        project.getRequiredSkills().add(skillopt.get());
        projectRepository.save(project);
        return "Skill added to the project";
    }

    public String changeProjectWorkspace(UUID projectId,Integer workspaceNumber){
        Optional<Project> projectOpt = projectRepository.findById(projectId);
        if (projectOpt.isEmpty()) {
            return "The project with that id does not exist";
        }
        Optional<Workspace> workspaceOpt = workspaceRepository.findByWorkspaceNumber(workspaceNumber);
        if (workspaceOpt.isEmpty()) {
            return "The workspace with that workspaceNumber does not exist";
        }
        Project project = projectOpt.get();
        Workspace workspace = workspaceOpt.get();
        boolean alreadyThere = project.getWorkspace() != null && project.getWorkspace().getId().equals(workspace.getId());
        if (!alreadyThere && projectRepository.existsByWorkspace(workspace)) {
            return "The workspace is taken by another project";
        }
        project.setWorkspace(workspace);
        projectRepository.save(project);
        return "Workspace changed successfully";
    }
}
