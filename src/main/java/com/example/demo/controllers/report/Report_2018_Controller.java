package com.example.demo.controllers.report;


import com.example.demo.model.report.Report_2018;
import com.example.demo.repositories.report.Report_2018_Repository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static com.example.demo.controllers.report.Report_Controller.setUpdateVariables;

@Api(tags = "Report_2018")
@RestController
public class Report_2018_Controller {

    public final Report_2018_Repository report_2018_repository;

    public Report_2018_Controller(Report_2018_Repository report_2018_repository) {
        this.report_2018_repository = report_2018_repository;
    }

    @ApiOperation(value = "Get list of countries from 2018 report", response = List.class)
    @GetMapping(path = "/2018_report",
            produces = { "application/json", "application/xml" })
    public List<Report_2018> getAllReports()
    {
        return report_2018_repository.findAll();
    }

    @ApiOperation(value = "Get one country report from 2018 report", response = Report_2018.class)
    @GetMapping(value = "/2018_report/{Country}",
            produces = { "application/json", "application/xml" })
    public Report_2018 getCountry(@PathVariable String Country) {
        return report_2018_repository.findFirstByCountry(Country);
    }

    @ApiOperation(value = "Delete country report from 2018 report", response = String.class)
    @DeleteMapping(value = "/2018_report/delete/{Country}",
            produces = { "application/json", "application/xml" })
    public String deleteCountry(@PathVariable String Country) {
        report_2018_repository.deleteReport_2018ByCountry(Country);
        return Country + " is deleted";
    }

    @ApiOperation(value = "Update country report from 2018 report", response = ResponseEntity.class)
    @PutMapping(value = "/2018_report/update/{Country}",
            produces = { "application/json", "application/xml" },
            consumes = { "application/json", "application/xml" })
    public ResponseEntity<Report_2018> updateCountry(@Validated @RequestBody Report_2018 reportDetails, @PathVariable String Country)
    {
        Optional<Report_2018> reportData = Optional.ofNullable(report_2018_repository.findFirstByCountry(Country));
        if(reportData.isPresent())
        {
            Report_2018 existingReport = reportData.get();
            setUpdateVariables(reportDetails, existingReport);
            return new ResponseEntity<>(report_2018_repository.save(existingReport), HttpStatus.OK);
        }
        else
        {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    @ApiOperation(value = "Create country report report for 2018 report", response = ResponseEntity.class)
    @PostMapping(value = "/2018_report/create",
            produces = { "application/json", "application/xml" },
            consumes = { "application/json", "application/xml" })
    public ResponseEntity<Report_2018> createReport(@RequestBody Report_2018 report)
    {
        boolean isReportFound = false;
        for (Report_2018 reports : getAllReports()) {
            if (reports.getCountry().contains(report.getCountry())) {
                isReportFound = true;
            }
        }
        if(!isReportFound) {
            try {
                Report_2018 newReport = report_2018_repository
                        .save(report);
                return new ResponseEntity<>(newReport, HttpStatus.CREATED);
            } catch (Exception e) {
                return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
            return new ResponseEntity<>(null, HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }
}
