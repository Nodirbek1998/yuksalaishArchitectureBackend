package uz.cas.controllersestem.payload.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.sql.Date;
import java.util.List;

public class ReqProject {
    @NotBlank(message = "malumot kiritilmagan")
    private String projectName;
    @NotEmpty(message = "malumot kiritilmagan")
    private List<Integer> usersList;
    @NotNull(message = "malumot kiritilmagan")
    private Integer projectManager;
    @NotNull(message = "malumot kiritilmagan")
    private Date projectCreated;
    @NotNull(message = "malumot kiritilmagan")
    private Date projectFinished;

    public ReqProject() {
    }

    public ReqProject(String projectName, List<Integer> usersList, Integer projectManager,  Date projectCreated, Date projectFinished) {
        this.projectName = projectName;
        this.usersList = usersList;
        this.projectManager = projectManager;
        this.projectCreated = projectCreated;
        this.projectFinished = projectFinished;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public List<Integer> getUsersList() {
        return usersList;
    }

    public void setUsersList(List<Integer> usersList) {
        this.usersList = usersList;
    }

    public Integer getProjectManager() {
        return projectManager;
    }

    public void setProjectManager(Integer projectManager) {
        this.projectManager = projectManager;
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

    public void setProjectFinished(Date projectFinished) {
        this.projectFinished = projectFinished;
    }
}
