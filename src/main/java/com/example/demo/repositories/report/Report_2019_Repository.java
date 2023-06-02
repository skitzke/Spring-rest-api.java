package com.example.demo.repositories.report;

import com.example.demo.model.report.Report_2019;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface Report_2019_Repository extends MongoRepository<Report_2019, String> {
    @Query("{ 'Country' : ?0 }")
    Report_2019 findReport_2019ByCountry(String Country);

    @Query("{'Country' : ?0 }")
    Report_2019 deleteReport_2019ByCountry(String Country);
}
