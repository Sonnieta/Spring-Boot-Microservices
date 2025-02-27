package com.Backend.Schoolmgt.Service;

import com.Backend.Schoolmgt.Entity.Subject;
import com.Backend.Schoolmgt.Repository.SubjectRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SubjectService {
    private final SubjectRepository subjectRepository;

    public SubjectService(SubjectRepository subjectRepository) {
        this.subjectRepository = subjectRepository;
    }

    public Subject createSubject(Subject subject) {
        return subjectRepository.save(subject);
    }

    public List<Subject> getAllSubjects() {
        return subjectRepository.findAll();
    }

    public Optional<Subject> getSubjectById(Long id) {
        return subjectRepository.findById(id);
    }

    public Subject updateSubject(Long id, Subject subject) {
        if (subjectRepository.existsById(id)) {
            subject.setId(id);
            return subjectRepository.save(subject);
        }
        return null;
    }

    public void deleteSubject(Long id) {
        subjectRepository.deleteById(id);
    }
}