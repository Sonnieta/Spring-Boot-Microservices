package com.Service.Students.Service;

import com.Service.Students.Client.SchoolmgtClient;
import com.Service.Students.Dto.ClassResponse;
import com.Service.Students.Dto.StreamResponse;
import com.Service.Students.Dto.StudentResponse;
import com.Service.Students.Entity.Student;
import com.Service.Students.Repository.StudentRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.Year;
import java.util.List;
import java.util.Random;

@Service
public class StudentService {

    private final SchoolmgtClient schoolmgtClient; // Feign Client for communication
    private final StudentRepository studentRepository; // Repository for database access

    public StudentService(SchoolmgtClient schoolmgtClient, StudentRepository studentRepository) {
        this.schoolmgtClient = schoolmgtClient;
        this.studentRepository = studentRepository;
    }
    /**
     * Register a new student and generate a unique admission number.
     */
    public Student registerStudent(Student student) {
        // Ensure unique admission number
        String admissionNumber;
        do {
            admissionNumber = generateAdmissionNumber();
        } while (studentRepository.existsByAdmissionNumber(admissionNumber));

        student.setAdmissionNumber(admissionNumber);
        return studentRepository.save(student);
    }

    /**
     * Fetch all students from the database.
     */
    @Transactional
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    /**
     * Fetch student details along with class and stream information.
     */
    /**
     * Fetch student details along with class and stream information.
     */
    public StudentResponse getStudentByAdmissionNumber(String admissionNumber) {
        // Fetch student by admission number
        Student student = studentRepository.findByAdmissionNumber(admissionNumber)
                .orElseThrow(() -> new RuntimeException("Student not found with admission number: " + admissionNumber));

        // Fetch related details from SchoolMgtService
        ClassResponse classResponse = schoolmgtClient.getClassById(student.getClassId());
        StreamResponse streamResponse = schoolmgtClient.getStreamById(student.getStreamId());

        // Convert to DTO response
        return StudentResponse.builder()
                .admissionNumber(student.getAdmissionNumber())
                .name(student.getName())
                .className(classResponse.getClassName())
                .streamName(streamResponse.getStreamName())
                .build();
    }


    /**
     * Generate a unique admission number in the format: ADM20241234
     */
    private String generateAdmissionNumber() {
        int uniqueNumber = 1000 + new Random().nextInt(9000); // Random 4-digit number
        return "ADM" + Year.now().getValue() + uniqueNumber;
    }
    public StudentResponse updateStudent(String admissionNumber, Student updatedStudent) {
        Student student = studentRepository.findByAdmissionNumber(admissionNumber)
                .orElseThrow(() -> new RuntimeException("Student not found with admission number: " + admissionNumber));

        // Update only non-null fields
        if (updatedStudent.getName() != null) student.setName(updatedStudent.getName());
        if (updatedStudent.getClassId() != null) student.setClassId(updatedStudent.getClassId());
        if (updatedStudent.getStreamId() != null) student.setStreamId(updatedStudent.getStreamId());

        Student savedStudent = studentRepository.save(student);

        // Fetch updated details
        ClassResponse classResponse = schoolmgtClient.getClassById(savedStudent.getClassId());
        StreamResponse streamResponse = schoolmgtClient.getStreamById(savedStudent.getStreamId());

        return StudentResponse.builder()
                .admissionNumber(savedStudent.getAdmissionNumber())
                .name(savedStudent.getName())
                .className(classResponse.getClassName())
                .streamName(streamResponse.getStreamName())
                .build();
    }

    public void deleteStudent(String admissionNumber) {
        Student student = studentRepository.findByAdmissionNumber(admissionNumber)
                .orElseThrow(() -> new RuntimeException("Student not found with admission number: " + admissionNumber));

        studentRepository.delete(student);
    }

        public List<ClassResponse> getAllClasses() {
            return schoolmgtClient.getAllClasses();
        }

        /**
         * New method: Fetch all streams from SchoolMgtService.
         */
        public List<StreamResponse> getAllStreams() {
            return schoolmgtClient.getAllStreams();
        }


    }




