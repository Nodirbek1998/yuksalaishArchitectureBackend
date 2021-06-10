package uz.cas.controllersestem.payload.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class ReqProgress {
    @NotNull
    private float percent;

    private Integer userId;
    @NotNull
    private Integer projectId;

    public ReqProgress() {
    }

    public ReqProgress(float percent, Integer userId, Integer projectId) {
        this.percent = percent;
        this.userId = userId;
        this.projectId = projectId;
    }

    public float getPercent() {
        return percent;
    }

    public void setPercent(float percent) {
        this.percent = percent;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }
}
