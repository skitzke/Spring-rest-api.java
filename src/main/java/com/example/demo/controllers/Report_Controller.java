package com.example.demo.controllers;

import com.example.demo.model.Report;
import com.example.demo.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/reports")
public class Report_Controller {

    @Autowired
    private ReportService reportService;

    @PostMapping("/add")
    public ResponseEntity<Report> addReport(@Valid @RequestBody Report report) {
        reportService.saveReport(report);
        return ResponseEntity.ok(report);
    }

    @GetMapping("/year/{year}")
    public ResponseEntity<List<Report>> getReportsByYear(@PathVariable int year) {
        List<Report> reports = reportService.getReportsByYear(year);
        return ResponseEntity.ok(reports);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Report>> getAllReports() {
        List<Report> reports = reportService.getAllReports();
        return ResponseEntity.ok(reports);
    }
}
