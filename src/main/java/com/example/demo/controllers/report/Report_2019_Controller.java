package com.example.demo.controllers.report;

import com.example.demo.model.report.Report_2016;
import com.example.demo.model.report.Report_2019;
import com.example.demo.repositories.report.Report_2019_Repository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static com.example.demo.controllers.report.Report_Controller.setUpdateVariables;

@Api(tags = "Report_2019")
@RestController
public class Report_2019_Controller {

    public final Report_2019_Repository report_2019_repository;

    public Report_2019_Controller(Report_2019_Repository report_2019_repository) {
        this.report_2019_repository = report_2019_repository;
    }

    @ApiOperation(value = "Get list of countries from 2019 report", response = List.class)
    @GetMapping(path = "/2019_report",
            produces = { "application/json", "application/xml" })
    public List<Report_2019> getAllReports()
    {
        return report_2019_repository.findAll();
    }

    @ApiOperation(value = "Get one country report from 2019 report", response = Report_2016.class)
    @GetMapping(value = "/2019_report/{Country}",
            produces = { "application/json", "application/xml" })
    public Report_2019 getCountry(@PathVariable String Country) {
        return report_2019_repository.findReport_2019ByCountry(Country);
    }

    @ApiOperation(value = "Delete country report from 2019 report", response = String.class)
    @DeleteMapping(value = "/2019_report/delete/{Country}",
            produces = { "application/json", "application/xml" })
    public String deleteCountry(@PathVariable String Country) {
        report_2019_repository.deleteReport_2019ByCountry(Country);
        return Country + " is deleted";
    }

    @ApiOperation(value = "Update country report from 2019 report", response = ResponseEntity.class)
    @PutMapping(value = "/2019_report/update/{Country}",
            produces = { "application/json", "application/xml" },
            consumes = { "application/json", "application/xml" })
    public ResponseEntity<Report_2019> updateCountry(@Validated @RequestBody Report_2019 reportDetails, @PathVariable String Country)
    {
        Optional<Report_2019> reportData = Optional.ofNullable(report_2019_repository.findReport_2019ByCountry(Country));
        if(reportData.isPresent())
        {
            Report_2019 existingReport = reportData.get();
            setUpdateVariables(reportDetails, existingReport);
            return new ResponseEntity<>(report_2019_repository.save(existingReport), HttpStatus.OK);
        }
        else
        {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @ApiOperation(value = "Create country report report for 2019 report", response = ResponseEntity.class)
    @PostMapping(value = "/2019_report/create",
            produces = { "application/json", "application/xml" },
            consumes = { "application/json", "application/xml" })
    public ResponseEntity<Report_2019> createReport(@RequestBody Report_2019 report)
    {
        boolean isReportFound = false;
        for (Report_2019 reports : getAllReports()) {
            if (reports.getCountry().contains(report.getCountry())) {
                isReportFound = true;
            }
        }
        if(!isReportFound){
            try {
                Report_2019 newReport = report_2019_repository
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