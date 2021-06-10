package uz.cas.controllersestem.entity;


import uz.cas.controllersestem.entity.template.AbsEntity;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;


@Entity
public class Comment extends AbsEntity{

    @ManyToOne
    private Users users;

    @ManyToOne
    private Project project;

    private String comment;

    private boolean status;

    public Comment() {
    }

    public Comment(Users users, Project project, String comment, boolean status) {
        this.users = users;
        this.project = project;
        this.comment = comment;
        this.status = status;
    }

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
