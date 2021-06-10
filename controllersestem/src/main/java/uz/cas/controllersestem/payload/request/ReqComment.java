package uz.cas.controllersestem.payload.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class ReqComment {
    @NotBlank
    private String comment;
    @NotNull
    private Integer projectId;

    public ReqComment() {
    }

    public ReqComment(String comment,  Integer projectId) {
        this.comment = comment;
        this.projectId = projectId;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }
}
