package uz.cas.controllersestem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.parameters.P;
import uz.cas.controllersestem.entity.Project;
import uz.cas.controllersestem.entity.Users;
import uz.cas.controllersestem.entity.enums.ProjectStatus;

import java.util.List;


public interface ProjectRepository extends JpaRepository<Project, Integer> {

    List<Project> findByProjectManager(Users projectManager);
    List<Project> findByProjectStatus(ProjectStatus projectStatus);




}
