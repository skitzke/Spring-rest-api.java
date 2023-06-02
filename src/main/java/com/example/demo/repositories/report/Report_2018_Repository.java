package com.example.demo.repositories.report;

import com.example.demo.model.report.Report_2018;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface Report_2018_Repository extends MongoRepository<Report_2018, String> {
    @Query("{ 'Country' : ?0 }")
    Report_2018 findFirstByCountry(String Country);

    @Query("{'Country' : ?0 }")
    Report_2018 deleteReport_2018ByCountry(String Country);
}
