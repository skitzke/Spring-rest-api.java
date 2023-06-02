package com.example.demo.model.report;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "2015_report")
@Document(collection = "2015_report")
public class Report {
    @Id
    private String id;
    @Indexed(name = "Country")
    private String Country;
    private int Happiness_Rank;
    private double Happiness_Score;
    private double Economy;
    private double Family;
    private double Health;
    private double Freedom;
    private double Trust;
    private double Generosity;

    public Report() {
        super();
    }

    public Report(String id, String country, int happiness_Rank, double happiness_Score, double economy, double family, double health, double freedom, double trust, double generosity) {
        id = id;
        Country = country;
        Happiness_Rank = happiness_Rank;
        Happiness_Score = happiness_Score;
        Economy = economy;
        Family = family;
        Health = health;
        Freedom = freedom;
        Trust = trust;
        Generosity = generosity;
    }

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

    public String getCountry() {
        return Country;
    }

    public void setCountry(String country) {
        Country = country;
    }

    public int getHappiness_Rank() {
        return Happiness_Rank;
    }

    public void setHappiness_Rank(int happiness_Rank) {
        Happiness_Rank = happiness_Rank;
    }

    public double getHappiness_Score() {
        return Happiness_Score;
    }

    public void setHappiness_Score(double happiness_Score) {
        Happiness_Score = happiness_Score;
    }

    public double getEconomy() {
        return Economy;
    }

    public void setEconomy(double economy) {
        Economy = economy;
    }

    public double getFamily() {
        return Family;
    }

    public void setFamily(double family) {
        Family = family;
    }

    public double getHealth() {
        return Health;
    }

    public void setHealth(double health) {
        Health = health;
    }

    public double getFreedom() {
        return Freedom;
    }

    public void setFreedom(double freedom) {
        Freedom = freedom;
    }

    public double getTrust() {
        return Trust;
    }

    public void setTrust(double trust) {
        Trust = trust;
    }

    public double getGenerosity() {
        return Generosity;
    }

    public void setGenerosity(double generosity) {
        Generosity = generosity;
    }

}