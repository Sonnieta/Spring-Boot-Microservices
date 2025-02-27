package com.Service.Students.Dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;
@Getter
@Setter
public class ReportDto {
    private String studentName;
    private String admissionNumber;
    private Map<String, Integer> subjectScores;
    private double averageScore;
    private String overallGrade;

    public ReportDto(String studentName, String admissionNumber, Map<String, Integer> subjectScores, double averageScore, String overallGrade) {
        this.studentName = studentName;
        this.admissionNumber = admissionNumber;
        this.subjectScores = subjectScores;
        this.averageScore = averageScore;
        this.overallGrade = overallGrade;
    }

    // Getters and Setters
}
