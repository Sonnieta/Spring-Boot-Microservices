package com.Service.Students.Dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Setter
@Getter
public class GradingResponse {
    // Getters and Setters
    private int minScore;
    private int maxScore;
    private String grade;
    private int points;

    // Constructors
    public GradingResponse() {}
    public GradingResponse(int minScore, int maxScore, String grade, int points) {
        this.minScore = minScore;
        this.maxScore = maxScore;
        this.grade = grade;
        this.points = points;
    }

}

