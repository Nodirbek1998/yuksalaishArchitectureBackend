package uz.cas.controllersestem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.cas.controllersestem.entity.Comment;
import uz.cas.controllersestem.entity.Users;
import uz.cas.controllersestem.payload.ReqComment;
import uz.cas.controllersestem.repository.CommentRepository;
import uz.cas.controllersestem.repository.ProjectRepository;
import uz.cas.controllersestem.repository.UsersRepository;
import uz.cas.controllersestem.security.JwtProvider;

import java.util.Optional;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private ProjectRepository projectRepository;
    @Autowired
    private UsersRepository usersRepository;
    @Autowired
    private JwtProvider jwtProvider;
    @Autowired
    private UsersService usersService;

    public ResponseEntity<?> addComment(ReqComment reqComment) {
        Comment editComment = new Comment();
        Users users = usersService.loadUserByUsername(jwtProvider.getUsername());
        Optional<Comment> byStatus = commentRepository.findByStatusAndUsers(true, users);
        if (byStatus.isPresent()) {
            editComment = byStatus.get();
            editComment.setStatus(false);
            commentRepository.save(editComment);
        }
        Comment comment = new Comment();
        comment.setComment(reqComment.getComment());
        comment.setProject(projectRepository.findById(reqComment.getProjectId()).get());
        comment.setUsers(usersRepository.findById(reqComment.getUserId()).get());
        comment.setStatus(true);
        commentRepository.save(comment);

        return ResponseEntity.status(200).body("Saqlandi");
    }
}
