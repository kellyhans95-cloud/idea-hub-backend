package rw.auca.idea_hub.domain;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "skill")
public class Skill {

    @Id
    @GeneratedValue (strategy = GenerationType.UUID)
    private UUID id;

    @Column (name = "name")
    private String name;


    @ManyToMany (mappedBy = "requiredSkills")
    @JsonIgnore 
    private java.util.List<Project> projects;
    

    public Skill() {
    }
    
    public UUID getId() {
        return id;
    }
    
    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public java.util.List<Project> getProjects() {
        return projects;
    }

    public void setProjects(java.util.List<Project> projects) {
        this.projects = projects;
    }

    

}
