package uz.cas.controllersestem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.cas.controllersestem.entity.Comment;
import uz.cas.controllersestem.entity.Project;
import uz.cas.controllersestem.entity.Users;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CommentRepository extends JpaRepository<Comment, UUID> {

    Optional<Comment> findByStatusAndUsers(boolean status, Users users);

    Optional<Comment> findByStatusAndProjectAndUsers(boolean status, Project project, Users users);
}
