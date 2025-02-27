package com.Service.Students.Dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StudentResponse {
    private String admissionNumber;
    private String name;
    private Long classId;      // Add this field
    private String className;
    private Long streamId;     // Add this field
    private String streamName; // From Feign Client

    public Optional<ResponseEntity<Object>> map(Object o) {
        return Optional.empty();
    }
}
