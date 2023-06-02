package com.example.demo.repositories.report;

import com.example.demo.model.report.Report_2017;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface Report_2017_Repository extends MongoRepository<Report_2017, String> {
    @Query("{ 'Country' : ?0 }")
    Report_2017 findReport_2017ByCountry(String Country);

    @Query("{'Country' : ?0 }")
    Report_2017 deleteReport_2017ByCountry(String Country);
}
