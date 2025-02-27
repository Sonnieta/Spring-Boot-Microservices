package com.Service.Students.Dto;

import lombok.*;

@Setter
@Getter
@Data

public class ClassResponse {
    // Getters and Setters


    private Long id;
    @Getter
    private String name;

    // Constructors
    public ClassResponse() {}
    public ClassResponse(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getClassName() {
        return "";
    }
}
