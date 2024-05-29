package com.example.demo.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "report")
@Document(collection = "report")
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
    private int year;  // Added field to store the year

    public Report() {
        super();
    }

    public Report(String id, String country, int happiness_Rank, double happiness_Score, double economy, double family, double health, double freedom, double trust, double generosity, int year) {
        this.id = id;
        this.Country = country;
        this.Happiness_Rank = happiness_Rank;
        this.Happiness_Score = happiness_Score;
        this.Economy = economy;
        this.Family = family;
        this.Health = health;
        this.Freedom = freedom;
        this.Trust = trust;
        this.Generosity = generosity;
        this.year = year;
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
        this.Country = country;
    }

    public int getHappiness_Rank() {
        return Happiness_Rank;
    }

    public void setHappiness_Rank(int happiness_Rank) {
        this.Happiness_Rank = happiness_Rank;
    }

    public double getHappiness_Score() {
        return Happiness_Score;
    }

    public void setHappiness_Score(double happiness_Score) {
        this.Happiness_Score = happiness_Score;
    }

    public double getEconomy() {
        return Economy;
    }

    public void setEconomy(double economy) {
        this.Economy = economy;
    }

    public double getFamily() {
        return Family;
    }

    public void setFamily(double family) {
        this.Family = family;
    }

    public double getHealth() {
        return Health;
    }

    public void setHealth(double health) {
        this.Health = health;
    }

    public double getFreedom() {
        return Freedom;
    }

    public void setFreedom(double freedom) {
        this.Freedom = freedom;
    }

    public double getTrust() {
        return Trust;
    }

    public void setTrust(double trust) {
        this.Trust = trust;
    }

    public double getGenerosity() {
        return Generosity;
    }

    public void setGenerosity(double generosity) {
        this.Generosity = generosity;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
}
