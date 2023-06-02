package com.example.demo.repositories.report;

import com.example.demo.model.report.Report_2016;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Repository
@Component
public interface Report_2016_Repository extends MongoRepository<Report_2016, String> {
    @Query("{ 'Country' : ?0 }")
    Report_2016 findFirstByCountry(String Country);

    @Query("{'Country' : ?0 }")
    Report_2016 deleteReport_2016ByCountry(String Country);
}
