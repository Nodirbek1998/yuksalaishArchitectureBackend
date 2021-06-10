package uz.cas.controllersestem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import uz.cas.controllersestem.payload.ReqGetPercent;
import uz.cas.controllersestem.payload.ReqProgress;
import uz.cas.controllersestem.repository.ProgressRepository;
import uz.cas.controllersestem.service.ProgressService;

import java.util.UUID;

@Controller
@RequestMapping("/uz/cas/progress")
public class ProgressController {

    @Autowired
    private ProgressService progressService;
    @Autowired
    private ProgressRepository progressRepository;

    @PostMapping
    public HttpEntity<?> addProgress(@RequestBody ReqProgress reqProgress) {
        return ResponseEntity.ok(progressService.addProgress(reqProgress));
    }

    @PostMapping("/{id}")
    public HttpEntity<?> editProgress(@PathVariable UUID id, @RequestBody ReqProgress reqProgress) {
        return ResponseEntity.ok(progressService.editProgress(id, reqProgress));
    }

    @GetMapping("/add/{id}")
    public HttpEntity<?> pushProgress(@PathVariable UUID id) {
        return ResponseEntity.ok(progressService.progressActive(id));
    }

    @PostMapping("/getPercent")
    public HttpEntity<?> getPercent(@RequestBody ReqGetPercent reqGetPercent) {
        return ResponseEntity.ok(progressService.getPercent(reqGetPercent));
    }

    @PostMapping("/disabledProgress")
    public HttpEntity<?> disabledProgress(@RequestBody ReqGetPercent reqGetPercent) {
        return ResponseEntity.ok(progressService.getPercentGIP(reqGetPercent));
    }

}
