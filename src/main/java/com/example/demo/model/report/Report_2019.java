package com.example.demo.model.report;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "2019_report")
public class Report_2019 extends Report{
    public Report_2019(String id, String country, int happiness_Rank, double happiness_Score, double economy, double family, double health, double freedom, double trust, double generosity) {
        super(id, country, happiness_Rank, happiness_Score, economy, family, health, freedom, trust, generosity);
    }
}