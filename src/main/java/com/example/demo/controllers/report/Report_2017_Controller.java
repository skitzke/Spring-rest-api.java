package com.example.demo.controllers.report;

import com.example.demo.model.report.Report_2017;
import com.example.demo.repositories.report.Report_2017_Repository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static com.example.demo.controllers.report.Report_Controller.setUpdateVariables;

@Api(tags = "Report_2017")
@RestController
public class Report_2017_Controller {

    public final Report_2017_Repository report_2017_repository;

    public Report_2017_Controller(Report_2017_Repository report_2017_repository) {
        this.report_2017_repository = report_2017_repository;
    }

    @ApiOperation(value = "Get list of countries from 2017 report", response = List.class)
    @GetMapping(path = "/2017_report",
            produces = { "application/json", "application/xml" })
    public List<Report_2017> getAllReports()
    {
        return report_2017_repository.findAll();
    }

    @ApiOperation(value = "Get one country report from 2017 report", response = Report_2017.class)
    @GetMapping(value = "/2017_report/{Country}",
            produces = { "application/json", "application/xml" })
    public Report_2017 getCountry(@PathVariable String Country) {
        return report_2017_repository.findReport_2017ByCountry(Country);
    }

    @ApiOperation(value = "Delete country report from 2017 report", response = String.class)
    @DeleteMapping(value = "/2017_report/delete/{Country}",
            produces = { "application/json", "application/xml" })
    public String deleteCountry(@PathVariable String Country) {
        report_2017_repository.deleteReport_2017ByCountry(Country);
        return Country + " is deleted";
    }

    @ApiOperation(value = "Update country report from 2017 report", response = ResponseEntity.class)
    @PutMapping(value = "/2017_report/update/{Country}",
            produces = { "application/json", "application/xml" },
            consumes = { "application/json", "application/xml" })
    public ResponseEntity<Report_2017> updateCountry(@Validated @RequestBody Report_2017 reportDetails, @PathVariable String Country)
    {
        Optional<Report_2017> reportData = Optional.ofNullable(report_2017_repository.findReport_2017ByCountry(Country));
        if(reportData.isPresent())
        {
            Report_2017 existingReport = reportData.get();
            setUpdateVariables(reportDetails, existingReport);
            return new ResponseEntity<>(report_2017_repository.save(existingReport), HttpStatus.OK);
        }
        else
        {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    @ApiOperation(value = "Create country report report for 2017 report", response = ResponseEntity.class)
    @PostMapping(value = "/2017_report/create",
            produces = { "application/json", "application/xml" },
            consumes = { "application/json", "application/xml" })
    public ResponseEntity<Report_2017> createReport(@RequestBody Report_2017 report)
    {
        boolean isReportFound = false;
        for (Report_2017 reports : getAllReports())
            if (reports.getCountry().contains(report.getCountry())) {
                isReportFound = true;
                break;
            }
        if(!isReportFound){
            try {
                Report_2017 newReport = report_2017_repository
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

