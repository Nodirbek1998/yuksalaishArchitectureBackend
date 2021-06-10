package uz.cas.controllersestem.payload;

public class ReqProgress {

    private float percent;

    private Integer userId;

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
