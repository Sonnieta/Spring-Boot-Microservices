package com.Service.Students.Controller;

import com.Service.Students.Dto.ExamDto;
import com.Service.Students.Entity.Exam;
import com.Service.Students.Service.ExamService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/exams")
public class ExamController {

    private final ExamService examService;

    public ExamController(ExamService examService) {
        this.examService = examService;
    }

    @PostMapping("/{admissionNumber}")
    public ResponseEntity<Exam> createExam(@PathVariable String admissionNumber, @RequestBody ExamDto examDto) {
        Exam savedExam = examService.createExam(admissionNumber, examDto);
        return ResponseEntity.ok(savedExam);
    }

    @GetMapping("/{admissionNumber}")
    public ResponseEntity<List<Exam>> getStudentExams(@PathVariable String admissionNumber) {
        return ResponseEntity.ok(examService.getExamsByStudent(admissionNumber));
    }
}
