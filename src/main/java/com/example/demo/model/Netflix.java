package com.example.demo.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "netflix")
public class Netflix {
    @Id
    private String id;
    private int Show_id;
    private String Type;
    @Indexed(name = "Title")
    private String Title;
    private String Director;
    private String Cast;
    private String Country;
    private String Date_added;
    private short Release_year;
    private String Rating;
    private String Duration;
    private String Listed_in;
    private String Description;

    public Netflix()
    {
        super();
    }

    public Netflix(String id, int show_id, String type, String title, String director, String cast, String country, String date_added, short release_year, String rating, String duration, String listed_in, String description) {
        id = id;
        Show_id = show_id;
        Type = type;
        Title = title;
        Director = director;
        Cast = cast;
        Country = country;
        Date_added = date_added;
        Release_year = release_year;
        Rating = rating;
        Duration = duration;
        Listed_in = listed_in;
        Description = description;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getShow_id() {
        return Show_id;
    }

    public void setShow_id(int show_id) {
        Show_id = show_id;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getDirector() {
        return Director;
    }

    public void setDirector(String director) {
        Director = director;
    }

    public String getCast() {
        return Cast;
    }

    public void setCast(String cast) {
        Cast = cast;
    }

    public String getCountry() {
        return Country;
    }

    public void setCountry(String country) {
        Country = country;
    }

    public String getDate_added() {
        return Date_added;
    }

    public void setDate_added(String date_added) {
        Date_added = date_added;
    }

    public short getRelease_year() {
        return Release_year;
    }

    public void setRelease_year(short release_year) {
        Release_year = release_year;
    }

    public String getRating() {
        return Rating;
    }

    public void setRating(String rating) {
        Rating = rating;
    }

    public String getDuration() {
        return Duration;
    }

    public void setDuration(String duration) {
        Duration = duration;
    }

    public String getListed_in() {
        return Listed_in;
    }

    public void setListed_in(String listed_in) {
        Listed_in = listed_in;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }
}
