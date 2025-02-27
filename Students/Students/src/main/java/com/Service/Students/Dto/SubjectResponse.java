package com.Service.Students.Dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
@Data
@Setter
@Getter
public class SubjectResponse {
    // Getters and Setters
    private Long id;
    private String name;

    // Constructors
    public SubjectResponse() {}
    public SubjectResponse(Long id, String name) {
        this.id = id;
        this.name = name;
    }

}

