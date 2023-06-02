package com.example.demo.model.report;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "2018_report")
public class Report_2018 extends Report{
    public Report_2018(String id, String Country, int Happiness_Rank, double Happiness_Score, double Economy, double Family, double Health, double Freedom, double Trust, double Generosity) {
        super(id, Country, Happiness_Rank, Happiness_Score, Economy, Family, Health, Freedom, Trust, Generosity);
    }
}
