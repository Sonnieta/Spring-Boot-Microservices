package com.Backend.Schoolmgt.Service;

import com.Backend.Schoolmgt.Entity.ClassEntity;
import com.Backend.Schoolmgt.Repository.ClassRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClassService {
    private final ClassRepository classRepository;

    public ClassService(ClassRepository classRepository) {
        this.classRepository = classRepository;
    }

    // Create a new class
    public ClassEntity createClass(ClassEntity classEntity) {
        return classRepository.save(classEntity);
    }

    // Get all classes
    public List<ClassEntity> getAllClasses() {
        return classRepository.findAll();
    }

    // Get class by ID
    public Optional<ClassEntity> getClassById(Long id) {
        return classRepository.findById(id);
    }

    // Update class
    public ClassEntity updateClass(Long id, ClassEntity classEntity) {
        if (classRepository.existsById(id)) {
            classEntity.setId(id);
            return classRepository.save(classEntity);
        }
        return null;
    }

    // Delete class
    public void deleteClass(Long id) {
        classRepository.deleteById(id);
    }
}