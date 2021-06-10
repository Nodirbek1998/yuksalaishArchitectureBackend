package uz.cas.controllersestem.payload;

import java.util.UUID;

public class ReqGetPercent {

    private String userId;
    private Integer projectId;

    public ReqGetPercent() {
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public ReqGetPercent(String userId, Integer projectId) {
        this.userId = userId;
        this.projectId = projectId;
    }
}
