package uz.cas.controllersestem.payload.request;

import javax.validation.constraints.NotNull;


public class ReqGetPercent {
    @NotNull
    private Integer projectId;

    public ReqGetPercent() {
    }


    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public ReqGetPercent(Integer projectId) {
        this.projectId = projectId;
    }
}
