package com.Service.Students.Service;

import com.Service.Students.Dto.ExamDto;
import com.Service.Students.Entity.Exam;
import com.Service.Students.Entity.Student;
import com.Service.Students.Repository.ExamRepository;
import com.Service.Students.Repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ExamService {

    private final ExamRepository examRepository;
    private final StudentRepository studentRepository;

    public ExamService(ExamRepository examRepository, StudentRepository studentRepository) {
        this.examRepository = examRepository;
        this.studentRepository = studentRepository;
    }

    public Exam createExam(String admissionNumber, ExamDto examDto) {
        // Fetch the student using admission number
        Optional<Student> studentOptional = studentRepository.findByAdmissionNumber(admissionNumber);

        if (studentOptional.isPresent()) {
            Student student = studentOptional.get();

            // Create new Exam
            Exam exam = new Exam();
            exam.setSubject(examDto.getSubject());
            exam.setScore(examDto.getScore());
            exam.setGrade(examDto.getGrade());
            exam.setStudent(student); // Associate exam with student

            return examRepository.save(exam);
        } else {
            throw new RuntimeException("Student not found with admission number: " + admissionNumber);
        }
    }

    public List<Exam> getExamsByStudent(String admissionNumber) {
        return examRepository.findByStudent_AdmissionNumber(admissionNumber);
    }
}
