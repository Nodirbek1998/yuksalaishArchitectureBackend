package uz.cas.controllersestem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.cas.controllersestem.entity.Comment;
import uz.cas.controllersestem.entity.Users;
import uz.cas.controllersestem.payload.request.ReqComment;
import uz.cas.controllersestem.payload.request.ReqGetPercent;
import uz.cas.controllersestem.repository.CommentRepository;
import uz.cas.controllersestem.repository.ProjectRepository;
import uz.cas.controllersestem.repository.UsersRepository;
import uz.cas.controllersestem.security.JwtProvider;

import java.util.List;

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

        public ResponseEntity<?> addComment(ReqComment reqComment){
            Users users = usersService.loadUserByUsername(jwtProvider.getUsername());
            Comment comment = new Comment();
            comment.setComment(reqComment.getComment());
            comment.setProject(projectRepository.findById(reqComment.getProjectId()).get());
        comment.setUsers(users);
        comment.setStatus(true);
        commentRepository.save(comment);

        return ResponseEntity.status(200).body("Malumot saqlandi");
    }

    public ResponseEntity<?> getProRectorComment(ReqGetPercent reqGetPercent){
        Users users = usersService.loadUserByUsername(jwtProvider.getUsername());
        List<Comment> byProjectAndUsers = commentRepository.findByProjectAndUsers(projectRepository.findById(reqGetPercent.getProjectId()).get(), users);

        return ResponseEntity.ok(byProjectAndUsers);
    }
}
