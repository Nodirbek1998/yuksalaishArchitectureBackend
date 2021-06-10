package uz.cas.controllersestem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.cas.controllersestem.entity.Project;
import uz.cas.controllersestem.entity.enums.ProjectStatus;

import java.util.List;


public interface ProjectRepository extends JpaRepository<Project, Integer> {

    List<Project> findByProjectStatusOrProjectStatus(ProjectStatus projectStatus, ProjectStatus projectStatus2);

    List<Project> findByProjectStatus(ProjectStatus projectStatus);


}
