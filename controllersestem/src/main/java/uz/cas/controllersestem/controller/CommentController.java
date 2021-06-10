package uz.cas.controllersestem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import uz.cas.controllersestem.payload.ReqComment;
import uz.cas.controllersestem.service.CommentService;

@Controller
@RequestMapping("/uz/cas/comment")
public class CommentController {
    @Autowired
    private CommentService commentService;

    @PostMapping
    public HttpEntity<?> addComment(@RequestBody ReqComment reqComment) {
        return ResponseEntity.ok(commentService.addComment(reqComment));
    }
}
