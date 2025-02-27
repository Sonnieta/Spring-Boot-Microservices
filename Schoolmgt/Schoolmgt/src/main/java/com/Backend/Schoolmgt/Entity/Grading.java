package com.Backend.Schoolmgt.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
@Entity
@Data
@AllArgsConstructor
@Table(name = "grades")

public class Grading {
    // Getters and Setters
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int minScore;
    private int maxScore;
    private String grade;
    private int points;

    // Constructors
    public Grading() {}

    public Grading(int minScore, int maxScore, String grade, int points) {
        this.minScore = minScore;
        this.maxScore = maxScore;
        this.grade = grade;
        this.points = points;
    }

}