package uz.cas.controllersestem.payload;

import java.sql.Date;
import java.util.List;

public class ReqProject {
    private String projectName;
    private List<Integer> usersList;
    private Integer projectManager;
    private Date projectCreated;
    private Date projectFinished;

    public ReqProject() {
    }

    public ReqProject(String projectName, List<Integer> usersList, Integer projectManager, Date projectCreated, Date projectFinished) {
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
