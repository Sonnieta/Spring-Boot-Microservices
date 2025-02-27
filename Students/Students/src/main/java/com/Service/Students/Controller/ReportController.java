package com.Service.Students.Controller;


import com.Service.Students.Dto.ReportDto;
import com.Service.Students.Service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/reports")
public class ReportController {
    @Autowired
    private ReportService reportService;

    @GetMapping("/{admissionNumber}")
    public ResponseEntity<ReportDto> getStudentReport(@PathVariable String admissionNumber) {
        return ResponseEntity.ok(reportService.generateStudentReport(admissionNumber));
    }
}
