package com.Backend.Schoolmgt.Controller;

import com.Backend.Schoolmgt.Entity.Grading;
import com.Backend.Schoolmgt.Service.GradingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/gradings")
public class GradingController {

    private final GradingService gradingService;

    public GradingController(GradingService gradingService) {
        this.gradingService = gradingService;
    }

    @PostMapping
    public ResponseEntity<Grading> createGrading(@RequestBody Grading grading) {
        return ResponseEntity.ok(gradingService.createGrading(grading));
    }

    @GetMapping
    public ResponseEntity<List<Grading>> getAllGradings() {
        return ResponseEntity.ok(gradingService.getAllGradings());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Grading> getGradingById(@PathVariable Long id) {
        return gradingService.getGradingById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Grading> updateGrading(@PathVariable Long id, @RequestBody Grading grading) {
        Grading updated = gradingService.updateGrading(id, grading);
        return updated != null ? ResponseEntity.ok(updated) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGrading(@PathVariable Long id) {
        gradingService.deleteGrading(id);
        return ResponseEntity.noContent().build();
    }

    // New API endpoint to get grading by score
    @GetMapping("/score/{score}")
    public ResponseEntity<Grading> getGradingByScore(@PathVariable int score) {
        return gradingService.getGradingByScore(score)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
