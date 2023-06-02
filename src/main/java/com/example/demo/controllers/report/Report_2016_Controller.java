package com.example.demo.controllers.report;

import com.example.demo.model.report.Report;
import com.example.demo.model.report.Report_2016;
import com.example.demo.repositories.report.Report_2016_Repository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static com.example.demo.controllers.report.Report_Controller.setUpdateVariables;

@Api(tags = "Report_2016")
@RestController
public class Report_2016_Controller {
    public final Report_2016_Repository report_2016_repository;

    public Report_2016_Controller(Report_2016_Repository report_2016_repository) {
        this.report_2016_repository = report_2016_repository;
    }

    @ApiOperation(value = "Get list of countries from 2016 report", response = List.class)
    @GetMapping(path = "/2016_report",
            produces = { "application/json", "application/xml" })
    public List<Report_2016> getAllReports()
    {
        return report_2016_repository.findAll();
    }

    @ApiOperation(value = "Get one country report from 2016 report", response = Report_2016.class)
    @GetMapping(value = "/2016_report/{Country}",
            produces = { "application/json", "application/xml" })
    public Report_2016 getCountry(@PathVariable String Country) {
        return report_2016_repository.findFirstByCountry(Country);
    }

    @ApiOperation(value = "Delete country report from 2016 report", response = String.class)
    @DeleteMapping(value = "/2016_report/delete/{Country}",
            produces = { "application/json", "application/xml" })
    public String deleteCountry(@PathVariable String Country) {
        report_2016_repository.deleteReport_2016ByCountry(Country);
        return Country + " is deleted";
    }

    @ApiOperation(value = "Update country report from 2016 report", response = ResponseEntity.class)
    @PutMapping(value = "/2016_report/update/{Country}",
            produces = { "application/json", "application/xml" },
            consumes = { "application/json", "application/xml" })
    public ResponseEntity<Report_2016> updateCountry(@Validated @RequestBody Report_2016 reportDetails, @PathVariable String Country)
    {
        Optional<Report_2016> reportData = Optional.ofNullable(report_2016_repository.findFirstByCountry(Country));
        if(reportData.isPresent())
        {
            Report_2016 existingReport = reportData.get();
            setUpdateVariables(reportDetails, existingReport);
            return new ResponseEntity<>(report_2016_repository.save(existingReport), HttpStatus.OK);
        }
        else
        {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    @ApiOperation(value = "Create country report report for 2016 report", response = ResponseEntity.class)
    @PostMapping(value = "/2016_report/create",
            produces = { "application/json", "application/xml" },
            consumes = { "application/json", "application/xml" })
    public ResponseEntity<Report_2016> createReport(@RequestBody Report_2016 report)
    {
        boolean isReportFound = false;
        for (Report reports : getAllReports()) {
            if (reports.getCountry().contains(report.getCountry())) {
                isReportFound = true;
            }
        }
        if (!isReportFound) {
            try {
                Report_2016 newReport = report_2016_repository
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
