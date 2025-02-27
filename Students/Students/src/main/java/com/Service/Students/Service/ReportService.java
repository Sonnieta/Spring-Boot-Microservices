package com.Service.Students.Service;

import com.Service.Students.Dto.ReportDto;
import com.Service.Students.Entity.Exam;
import com.Service.Students.Entity.Student;
import com.Service.Students.Repository.ExamRepository;
import com.Service.Students.Repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

@Service
public class ReportService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private ExamRepository examRepository;

    public ReportDto generateStudentReport(String admissionNumber) {
        Student student = studentRepository.findByAdmissionNumber(admissionNumber)
                .orElseThrow(() -> new NoSuchElementException(
                        "Student with admission number " + admissionNumber + " not found"));

        List<Exam> exams = examRepository.findByStudent_AdmissionNumber(admissionNumber);
        if (exams.isEmpty()) {
            throw new NoSuchElementException("No exam records found for student with admission number " + admissionNumber);
        }

        Map<String, Integer> subjectScores = new HashMap<>();
        int totalScore = 0;

        for (Exam exam : exams) {
            subjectScores.put(exam.getSubject(), exam.getScore());
            totalScore += exam.getScore();
        }

        BigDecimal averageScore = BigDecimal.valueOf(totalScore)
                .divide(BigDecimal.valueOf(exams.size()), 2, RoundingMode.HALF_UP);
        String overallGrade = getGradeFromScore(averageScore);

        return new ReportDto(
                student.getName(),
                student.getAdmissionNumber(),
                subjectScores,
                averageScore.doubleValue(),
                overallGrade
        );
    }

    private String getGradeFromScore(BigDecimal score) {
        if (score.compareTo(BigDecimal.valueOf(80)) >= 0) return "A";
        if (score.compareTo(BigDecimal.valueOf(70)) >= 0) return "B";
        if (score.compareTo(BigDecimal.valueOf(60)) >= 0) return "C";
        if (score.compareTo(BigDecimal.valueOf(50)) >= 0) return "D";
        return "F";
    }
}
