package uz.cas.controllersestem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import uz.cas.controllersestem.entity.Comment;
import uz.cas.controllersestem.entity.Project;
import uz.cas.controllersestem.entity.Users;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CommentRepository extends JpaRepository<Comment, UUID> {

    @Query(value = "delete from comment where project_id = ?1", nativeQuery = true, name = "csdc")
    @Modifying
    void  deleteComment(Integer id);

    List<Comment> findByProjectAndUsers(Project project, Users users);



}
