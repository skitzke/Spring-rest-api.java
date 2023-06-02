package com.example.demo.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "imdb_movies")
public class Imdb {
    @Id
    private String id;
    private String Imdb_title_id;
    @Indexed(name = "Title")
    private String Title;
    private String Original_Title;
    private short Year;
    private String Date_Published;
    private String Genre;
    private short Duration;
    private String Country;
    private String Language;
    private String Director;
    private String Writer;
    private String Production;
    private String Actors;
    private String Description;
    private double Avg_Vote;
    private int Votes;
    private String Budget;
    private String Usa_Gross_Income;
    private String Worldwide_Gross_Income;
    private short Metascore;
    private int Reviews_Users;
    private int Reviews_Critics;

    public Imdb() {
        super();
    }

    public Imdb(String imdb_title_id, String title, String original_title, short year, String date_Published, String genre, short duration, String country, String language, String director, String writer, String production, String actors, String description, double avg_Vote, int votes, String budget, String usa_Gross_Income, String worldwide_Gross_Income, short metascore, int reviews_Users, int reviews_Critics) {
        Imdb_title_id = imdb_title_id;
        Title = title;
        Original_Title = original_title;
        Year = year;
        Date_Published = date_Published;
        Genre = genre;
        Duration = duration;
        Country = country;
        Language = language;
        Director = director;
        Writer = writer;
        Production = production;
        Actors = actors;
        Description = description;
        Avg_Vote = avg_Vote;
        Votes = votes;
        Budget = budget;
        Usa_Gross_Income = usa_Gross_Income;
        Worldwide_Gross_Income = worldwide_Gross_Income;
        Metascore = metascore;
        Reviews_Users = reviews_Users;
        Reviews_Critics = reviews_Critics;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImdb_title_id() {
        return Imdb_title_id;
    }

    public void setImdb_title_id(String imdb_title_id) {
        Imdb_title_id = imdb_title_id;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getOriginal_title() {
        return Original_Title;
    }

    public void setOriginal_title(String original_title) {
        Original_Title = original_title;
    }

    public short getYear() {
        return Year;
    }

    public void setYear(short year) {
        Year = year;
    }

    public String getDate_Published() {
        return Date_Published;
    }

    public void setDate_Published(String date_Published) {
        Date_Published = date_Published;
    }

    public String getGenre() {
        return Genre;
    }

    public void setGenre(String genre) {
        Genre = genre;
    }

    public short getDuration() {
        return Duration;
    }

    public void setDuration(short duration) {
        Duration = duration;
    }

    public String getCountry() {
        return Country;
    }

    public void setCountry(String country) {
        Country = country;
    }

    public String getLanguage() {
        return Language;
    }

    public void setLanguage(String language) {
        Language = language;
    }

    public String getDirector() {
        return Director;
    }

    public void setDirector(String director) {
        Director = director;
    }

    public String getWriter() {
        return Writer;
    }

    public void setWriter(String writer) {
        Writer = writer;
    }

    public String getProduction() {
        return Production;
    }

    public void setProduction(String production) {
        Production = production;
    }

    public String getActors() {
        return Actors;
    }

    public void setActors(String actors) {
        Actors = actors;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public double getAvg_Vote() {
        return Avg_Vote;
    }

    public void setAvg_Vote(double avg_Vote) {
        Avg_Vote = avg_Vote;
    }

    public int getVotes() {
        return Votes;
    }

    public void setVotes(int votes) {
        Votes = votes;
    }

    public String getBudget() {
        return Budget;
    }

    public void setBudget(String budget) {
        Budget = budget;
    }

    public String getUsa_Gross_Income() {
        return Usa_Gross_Income;
    }

    public void setUsa_Gross_Income(String usa_Gross_Income) {
        Usa_Gross_Income = usa_Gross_Income;
    }

    public String getWorldwide_Gross_Income() {
        return Worldwide_Gross_Income;
    }

    public void setWorldwide_Gross_Income(String worldwide_Gross_Income) {
        Worldwide_Gross_Income = worldwide_Gross_Income;
    }

    public short getMetascore() {
        return Metascore;
    }

    public void setMetascore(short metascore) {
        Metascore = metascore;
    }

    public int getReviews_Users() {
        return Reviews_Users;
    }

    public void setReviews_Users(int reviews_Users) {
        Reviews_Users = reviews_Users;
    }

    public int getReviews_Critics() {
        return Reviews_Critics;
    }

    public void setReviews_Critics(int reviews_Critics) {
        Reviews_Critics = reviews_Critics;
    }
}
