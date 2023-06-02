package com.example.demo.repositories.report;

import com.example.demo.model.report.Report;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Repository
@Component
public interface Report_2015_Repository extends MongoRepository<Report, String> {
    @Query("{ 'Country' : ?0 }")
    Report findFirstByCountry(String Country);

    @Query("{'Country' : ?0 }")
    Report deleteReport_2015ByCountry(String Country);
}
