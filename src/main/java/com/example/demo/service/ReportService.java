package com.example.demo.service;

import com.example.demo.model.Report;
import com.example.demo.repositories.ReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReportService {

    @Autowired
    private ReportRepository reportRepository;

    public Report saveReport(Report report) {
        return reportRepository.save(report);
    }

    public List<Report> getReportsByYear(int year) {
        return reportRepository.findByYear(year);
    }

    public List<Report> getAllReports() {
        return reportRepository.findAll();
    }
}
