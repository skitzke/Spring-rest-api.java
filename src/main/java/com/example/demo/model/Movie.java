package com.example.demo.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class Movie {

    @NotBlank(message = "Title is mandatory")
    private String title;

    @Size(max = 500, message = "Description must be less than 500 characters")
    private String description;

    // Constructors
    public Movie() {
    }

    public Movie(String title, String description) {
        this.title = title;
        this.description = description;
    }

    // Getters and Setters
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
