package com.Service.Students.Client;


import com.Service.Students.Dto.ClassResponse;
import com.Service.Students.Dto.GradingResponse;
import com.Service.Students.Dto.StreamResponse;
import com.Service.Students.Dto.SubjectResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "Schoolmgt")
public interface SchoolmgtClient {


    // Get all classes
    @GetMapping("/classes/{id}")
    ClassResponse getClassById(@PathVariable Long id);


    // Get all streams
    @GetMapping("/streams/{id}")
    StreamResponse getStreamById(@PathVariable Long id);

    @GetMapping("/classes")
    List<ClassResponse> getAllClasses();  // Fetch all classes from SchoolMgtService

    @GetMapping("/streams")
    List<StreamResponse> getAllStreams();  // Fetch all streams from SchoolMgtService

    // Get all subjects
    @GetMapping("/subjects/{id}")
    SubjectResponse getSubjectById(@PathVariable Long id);

    // Get grading system details
    @GetMapping("/gradings/{score}")
    GradingResponse getGradingById(@PathVariable Long id);
}
