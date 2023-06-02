package com.example.demo.controllers.report;

import com.example.demo.model.report.Report;
import com.example.demo.repositories.report.Report_2015_Repository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Api(tags = "Report_2015")
@RestController
public class Report_Controller {

    public final Report_2015_Repository report_repository;

    public Report_Controller(Report_2015_Repository report_repository) {
        this.report_repository = report_repository;
    }

    @ApiOperation(value = "Get list of countries from 2015 report", response = List.class)
    @GetMapping(
            value = "/2015_report",
            produces = { "application/json", "application/xml" })
    public List<Report> getAllReports()
    {
        return report_repository.findAll();
    }

    @ApiOperation(value = "Get one country report from 2015 report", response = Report.class)
    @GetMapping(
            value = "/2015_report/{Country}",
            produces = { "application/json", "application/xml" })
    public @ResponseBody Report getCountry(@PathVariable String Country) {
        return report_repository.findFirstByCountry(Country);
    }

    @ApiOperation(value = "Delete country report from 2015 report", response = String.class)
    @DeleteMapping(value = "/2015_report/delete/{Country}",
            produces = { "application/json", "application/xml" })
    public String deleteCountry(@PathVariable String Country) {
        report_repository.deleteReport_2015ByCountry(Country);
        return Country + " is deleted";
    }

    @ApiOperation(value = "Update country report from 2015 report", response = ResponseEntity.class)
    @PutMapping(value = "/2015_report/update/{Country}",
            produces = { "application/json", "application/xml" },
            consumes = { "application/json", "application/xml" })
    public ResponseEntity<Report> updateCountry(@Validated @RequestBody Report reportDetails, @PathVariable String Country)
    {
        Optional<Report> reportData = Optional.ofNullable(report_repository.findFirstByCountry(Country));
        if(reportData.isPresent())
        {
            Report existingReport = reportData.get();
            setUpdateVariables(reportDetails, existingReport);
            return new ResponseEntity<>(report_repository.save(existingReport), HttpStatus.OK);
        }
        else
        {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @ApiOperation(value = "Create country report report for 2015 report", response = ResponseEntity.class)
    @PostMapping(value = "/2015_report/create",
            produces = { "application/json", "application/xml" },
            consumes = { "application/json", "application/xml" })
    public ResponseEntity<Report> createReport(@RequestBody Report report) {
        boolean isReportFound = false;
        for (Report reports : getAllReports()) {
            if (reports.getCountry().contains(report.getCountry())) {
                isReportFound = true;
            }
        }
        if (!isReportFound) {
            try {
                Report newReport = report_repository
                        .save(report);
                return new ResponseEntity<>(newReport, HttpStatus.CREATED);
            } catch (Exception e) {
                return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
            return new ResponseEntity<>(null, HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    public static void setUpdateVariables(@RequestBody @Validated Report reportDetails, Report existingReport) {
        existingReport.setCountry(reportDetails.getCountry());
        existingReport.setEconomy(reportDetails.getEconomy());
        existingReport.setFamily(reportDetails.getFamily());
        existingReport.setFreedom(reportDetails.getFreedom());
        existingReport.setGenerosity(reportDetails.getGenerosity());
        existingReport.setHappiness_Rank(reportDetails.getHappiness_Rank());
        existingReport.setHappiness_Score(reportDetails.getHappiness_Score());
        existingReport.setTrust(reportDetails.getTrust());
        existingReport.setHealth(reportDetails.getHealth());
    }
}
