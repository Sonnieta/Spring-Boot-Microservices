package com.Service.Students.Repository;


import com.Service.Students.Entity.Exam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExamRepository extends JpaRepository<Exam, Long> {
    List<Exam> findByStudent_AdmissionNumber(String admissionNumber);
}