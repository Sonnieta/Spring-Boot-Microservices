package com.Service.Students.Controller;

import com.Service.Students.Client.SchoolmgtClient;
import com.Service.Students.Dto.*;
import com.Service.Students.Entity.Student;
import com.Service.Students.Service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/students")

public class StudentController {
    private final StudentService studentService;
    private final SchoolmgtClient schoolmgtClient;

    public StudentController(StudentService studentService, SchoolmgtClient schoolmgtClient) {
        this.studentService = studentService;
        this.schoolmgtClient = schoolmgtClient;

    }
    // Fetch all classes
    @GetMapping("/classes")
    public List<ClassResponse> getAllClasses() {
        return studentService.getAllClasses();
    }

    // Fetch all streams
    @GetMapping("/streams")
    public List<StreamResponse> getAllStreams() {
        return studentService.getAllStreams();
    }

    @GetMapping("/gradings")
    public ResponseEntity<GradingResponse> getGradings(@PathVariable Long id) {
        return ResponseEntity.ok(schoolmgtClient.getGradingById(id));
    }

    @GetMapping("/streams/{id}")
    public ResponseEntity<StreamResponse> getStreamById(@PathVariable Long id) {
        return ResponseEntity.ok(schoolmgtClient.getStreamById(id));
    }
    @GetMapping("/classes/{id}")
    public ResponseEntity<ClassResponse> getClassById(@PathVariable Long id) {
        return ResponseEntity.ok(schoolmgtClient.getClassById(id));
    }
    /**
     * Create a new student
     */
    @PostMapping("/register")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_TEACHER')")
    public ResponseEntity<Student> registerStudent(@RequestBody Student student) {
        Student savedStudent = studentService.registerStudent(student);
        return ResponseEntity.ok(savedStudent);
    }

    /**
     * Get all students
     */
    @GetMapping
    public ResponseEntity<List<Student>> getAllStudents() {
        List<Student> students = studentService.getAllStudents();
        return ResponseEntity.ok(students);
    }

    /**
     * Get a student by admission number
     */
    @GetMapping("/{admissionNumber}")
    public ResponseEntity<StudentResponse> getStudentByAdmissionNumber(@PathVariable String admissionNumber) {
        try {
            StudentResponse studentResponse = studentService.getStudentByAdmissionNumber(admissionNumber);
            return ResponseEntity.ok(studentResponse);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }



//    Update student details
     @PutMapping("/{admissionNumber}")
     public ResponseEntity<StudentResponse> updateStudent(@PathVariable String admissionNumber, @RequestBody Student updatedStudent) {
     try {
     StudentResponse studentResponse = studentService.updateStudent(admissionNumber, updatedStudent);
     return ResponseEntity.ok(studentResponse);
     } catch (RuntimeException e) {
     return ResponseEntity.notFound().build();
     }
     }

     @DeleteMapping("/{admissionNumber}")
     public ResponseEntity<Void> deleteStudent(@PathVariable String admissionNumber) {
     try {
     studentService.deleteStudent(admissionNumber);
     return ResponseEntity.noContent().build(); // 204 No Content (successful deletion)
     } catch (RuntimeException e) {
     return ResponseEntity.notFound().build();
     }
     }




}
