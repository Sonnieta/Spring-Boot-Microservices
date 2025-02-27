package com.Service.Students.Dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class ExamDto {
    private String subject;
    private int score;
    private String grade;
    // ✅ Only admission number required
}