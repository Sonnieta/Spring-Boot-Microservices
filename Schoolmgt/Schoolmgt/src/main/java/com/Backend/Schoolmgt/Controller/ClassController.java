package com.Backend.Schoolmgt.Controller;


import com.Backend.Schoolmgt.Entity.ClassEntity;
import com.Backend.Schoolmgt.Service.ClassService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/classes")
public class ClassController {
    private final ClassService classService;

    public ClassController(ClassService classService) {
        this.classService = classService;
    }

    @PostMapping("/create")
    public ClassEntity createClass(@RequestBody ClassEntity classEntity) {
        return classService.createClass(classEntity);
    }

    @GetMapping
    public List<ClassEntity> getAllClasses() {
        return classService.getAllClasses();
    }

    @GetMapping("/{id}")
    public Optional<ClassEntity> getClassById(@PathVariable Long id) {
        return classService.getClassById(id);
    }

    @PutMapping("/update/{id}")
    public ClassEntity updateClass(@PathVariable Long id, @RequestBody ClassEntity classEntity) {
        return classService.updateClass(id, classEntity);
    }

    @DeleteMapping("/{id}")
    public void deleteClass(@PathVariable Long id) {
        classService.deleteClass(id);
    }
}
