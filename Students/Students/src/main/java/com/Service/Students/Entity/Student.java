package com.Service.Students.Entity;

import com.Service.Students.Entity.ParentDetails;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.Year;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Student {

    @Id  // Admission Number as the Primary Key
    @Column(unique = true, nullable = false)
    private String admissionNumber;

    private String name;
    private String dateOfBirth;
    private String address;
    private String contact;

    private String classEntity;
    private String stream;
    private String enrollmentDate;

    @Embedded
    private ParentDetails parentDetails;

    @JsonIgnore
    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL)
    private List<Exam> exams;

    public Long getStreamId() {
        return 0L;
    }

    public Long getClassId() {
        return 0L;
    }

    public Long getId() {
        return 0L;
    }

    public void setClassId(Long classId) {
    }

    public void setStreamId(Long streamId) {

    }
}
