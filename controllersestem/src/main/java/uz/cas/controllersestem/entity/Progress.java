package uz.cas.controllersestem.entity;


import uz.cas.controllersestem.entity.enums.ProgressStatus;
import uz.cas.controllersestem.entity.enums.ProjectStatus;
import uz.cas.controllersestem.entity.template.AbsEntity;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;
import java.util.UUID;

@Entity
public class Progress extends AbsEntity {

    private float percent;
    @ManyToOne
    private Users users;
    @Enumerated(value = EnumType.STRING)
    private ProgressStatus status;
    @ManyToOne
    private Project project;


    public Progress() {
    }

    public Progress(float percent, Users users, ProgressStatus status, Project project) {
        this.percent = percent;
        this.users = users;
        this.status = status;
        this.project = project;
    }

    public float getPercent() {
        return percent;
    }

    public void setPercent(float percent) {
        this.percent = percent;
    }

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }

    public ProgressStatus getStatus() {
        return status;
    }

    public void setStatus(ProgressStatus status) {
        this.status = status;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

}
