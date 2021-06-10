package uz.cas.controllersestem.entity;


import uz.cas.controllersestem.entity.enums.ProjectStatus;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;


@Entity
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false)
    private String projectName;

    @ManyToMany
    private List<Users> usersList;

    @OneToOne
    private Users projectManager;

    private float projectPercent;

    private Date projectCreated;

    private Date projectFinished;

    private boolean projectMake;

    @Enumerated(EnumType.STRING)
    private ProjectStatus projectStatus;

    @ManyToMany
    private List<Document> documents;

    public Project() {
    }

    public Project(String projectName,
                   List<Users> usersList,
                   Users projectManager,
                   float projectPercent,
                   Date projectCreated,
                   Date projectFinished,
                   boolean projectMake,
                   ProjectStatus projectStatus,
                   List<Document> documents) {
        this.projectName = projectName;
        this.usersList = usersList;
        this.projectManager = projectManager;
        this.projectPercent = projectPercent;
        this.projectCreated = projectCreated;
        this.projectFinished = projectFinished;
        this.projectMake = projectMake;
        this.projectStatus = projectStatus;
        this.documents = documents;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public List<Users> getUsersList() {
        return usersList;
    }

    public void setUsersList(List<Users> usersList) {
        this.usersList = usersList;
    }

    public Users getProjectManager() {
        return projectManager;
    }

    public void setProjectManager(Users projectManager) {
        this.projectManager = projectManager;
    }

    public float getProjectPercent() {
        return projectPercent;
    }

    public void setProjectPercent(float projectPercent) {
        this.projectPercent = projectPercent;
    }

    public Date getProjectCreated() {
        return projectCreated;
    }

    public void setProjectCreated(Date projectCreated) {
        this.projectCreated = projectCreated;
    }

    public Date getProjectFinished() {
        return projectFinished;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setProjectFinished(Date projectFinished) {
        this.projectFinished = projectFinished;
    }

    public boolean isProjectMake() {
        return projectMake;
    }

    public void setProjectMake(boolean projectMake) {
        this.projectMake = projectMake;
    }

    public ProjectStatus getProjectStatus() {
        return projectStatus;
    }

    public void setProjectStatus(ProjectStatus projectStatus) {
        this.projectStatus = projectStatus;
    }

    public List<Document> getDocuments() {
        return documents;
    }

    public void setDocuments(List<Document> documents) {
        this.documents = documents;
    }
}
