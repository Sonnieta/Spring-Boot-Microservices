package com.Service.Students.Dto;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class StreamResponse {
    @Setter
    private Long id;
    private String name;

    // Constructors
    public StreamResponse() {}
    public StreamResponse(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getStreamName() {
        return "";
    }
}

