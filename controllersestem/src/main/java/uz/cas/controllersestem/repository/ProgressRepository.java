package uz.cas.controllersestem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import uz.cas.controllersestem.entity.Progress;
import uz.cas.controllersestem.entity.Project;
import uz.cas.controllersestem.entity.Users;
import uz.cas.controllersestem.entity.enums.ProgressStatus;

import java.util.List;
import java.util.UUID;

public interface ProgressRepository extends JpaRepository<Progress, UUID> {

    List<Progress> findByStatusAndProject(ProgressStatus status, Project project);

    List<Progress> findByStatusAndProjectAndUsers(ProgressStatus status, Project project, Users users);
    @Modifying
    @Query(value = "delete from progress where project_id = ?1", nativeQuery = true, name = "progress")
    void  deleteProgress(Integer id);


}
