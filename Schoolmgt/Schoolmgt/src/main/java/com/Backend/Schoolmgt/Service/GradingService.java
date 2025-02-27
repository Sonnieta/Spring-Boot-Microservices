package com.Backend.Schoolmgt.Service;

import com.Backend.Schoolmgt.Entity.Grading;
import com.Backend.Schoolmgt.Repository.GradingRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GradingService {
    private final GradingRepository gradingRepository;

    public GradingService(GradingRepository gradingRepository) {
        this.gradingRepository = gradingRepository;
    }

    public Grading createGrading(Grading grading) {
        return gradingRepository.save(grading);
    }

    public List<Grading> getAllGradings() {
        return gradingRepository.findAll();
    }

    public Optional<Grading> getGradingById(Long id) {
        return gradingRepository.findById(id);
    }

    public Grading updateGrading(Long id, Grading grading) {
        if (gradingRepository.existsById(id)) {
            grading.setId(id);
            return gradingRepository.save(grading);
        }
        return null;
    }

    public void deleteGrading(Long id) {
        gradingRepository.deleteById(id);
    }

    // New method to get grade by score
    public Optional<Grading> getGradingByScore(int score) {
        return gradingRepository.findByScore(score);
    }
}
