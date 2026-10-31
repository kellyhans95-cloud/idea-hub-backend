package rw.auca.idea_hub.domain;

import java.time.LocalDate;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity 
@Table (name = "member_assignment")
public class MemberAssignment {

    @Id 
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "role_name" )
    private String roleName;

    @Column(name = "notes")
    private String notes;

    @Column(name = "hours_per_week")
    private int hoursPerWeek;

    @Column(name = "assigned_date")
    private  LocalDate assignedDate;

    @ManyToOne
    @JoinColumn (name = "collaboration_request_id")
    private CollaborationRequest collaborationRequest;

    public MemberAssignment(){

    }

    public UUID getId(){
        return id;
    }
    public void setId(UUID id){
        this.id = id;
    }

    public String getRoleName(){
        return roleName;
    }
    public void setRoleName(String roleName){
        this.roleName = roleName;
    }

    public String getNotes(){
        return notes;
    }
    public void setNotes(String notes){
        this.notes = notes;
    }

    public int getHoursPerweek(){
        return hoursPerWeek;
    }
    public void setHoursPerweek(int hoursPerweek){
        this.hoursPerWeek = hoursPerWeek;
    }

    public LocalDate getAssignedDate(){
        return assignedDate;
    }
    public void setAssignedDate(LocalDate assignedDate){
        this.assignedDate = assignedDate;
    }

    public CollaborationRequest getCollaborationRequest(){
        return collaborationRequest;
    }
    public void setCollaborationRequest(CollaborationRequest collaborationRequest){
        this.collaborationRequest = collaborationRequest;
    }
}
