package rw.auca.idea_hub.domain;

import java.time.LocalDate;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity 
@Table (name = "collaboration_request")
public class CollaborationRequest {
    

    @Id 
    @GeneratedValue (strategy = jakarta.persistence.GenerationType.UUID)
    private UUID id;

    @Column (name = "request_date")
    private LocalDate requestDate;

    @Enumerated (EnumType.STRING)
    @Column (name = "status")
    private RequestStatus status;
    
    @Column (name = "message")
    private String message;

    @ManyToOne
    @JoinColumn (name = "student_id")
    private Student student;

    @ManyToOne
    @JoinColumn (name = "project_id")
    private Project project;

    public CollaborationRequest() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public LocalDate getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(LocalDate requestDate) {
        this.requestDate = requestDate;
    }

    public RequestStatus getStatus() {
        return status;
    }

    public void setStatus(RequestStatus status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    
}
