package uz.cas.controllersestem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import uz.cas.controllersestem.payload.request.ReqActivePercent;
import uz.cas.controllersestem.payload.request.ReqGetPercent;
import uz.cas.controllersestem.payload.request.ReqProgress;
import uz.cas.controllersestem.service.ProgressService;

import javax.validation.Valid;
import java.util.UUID;

@Controller
@RequestMapping("/uz/cas/progress")
public class ProgressController {

    @Autowired
    private ProgressService progressService;
    @PostMapping
    public HttpEntity<?> addProgress(@Valid  @RequestBody ReqProgress reqProgress){
        return ResponseEntity.ok(progressService.addProgress(reqProgress));
    }

    @PostMapping("/{id}")
    public HttpEntity<?> editProgress(@PathVariable UUID id, @Valid  @RequestBody ReqProgress reqProgress){
        return ResponseEntity.ok(progressService.editProgress(id, reqProgress));
    }

    @PostMapping("/add/{id}")
    public HttpEntity<?> pushProgress(@PathVariable UUID id, @RequestBody ReqActivePercent reqActivePercent){
        return ResponseEntity.ok(progressService.progressActive(id, reqActivePercent));
    }
    @PostMapping("/getPercent")
    public HttpEntity<?> getPercent(@RequestBody ReqGetPercent reqGetPercent){
        return ResponseEntity.ok(progressService.getPercentGIP(reqGetPercent));
    }
    @PostMapping("/disabledProgress")
    public HttpEntity<?> disabledProgress(@Valid @RequestBody ReqGetPercent reqGetPercent){
        return ResponseEntity.ok(progressService.getPercentGIP(reqGetPercent));
    }
    @DeleteMapping("/{id}")
    public HttpEntity<?> deleteProgress(@PathVariable UUID id){
        return ResponseEntity.ok(progressService.deleteProgress(id));

    }

}
