package com.example.demo.controllers;

import com.example.demo.model.Imdb;
import com.example.demo.repositories.Imdb_Repository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Api(tags = "IMDB")
@RestController
public class Imdb_Controller {

    public final Imdb_Repository imdb_repository;

    public Imdb_Controller(Imdb_Repository imdb_repository) {
        this.imdb_repository = imdb_repository;
    }

    @ApiOperation(value = "Get list of IMDB movies", response = List.class)
    @GetMapping(path = "/imdb_movies",
            produces = {"application/json", "application/xml"})
    public List<Imdb> getAllImdbs() {
        return imdb_repository.findAll();
    }

    @ApiOperation(value = "Get one IMDB movie by title", response = Imdb.class)
    @GetMapping(value = "/imdb_movies/{Title}",
            produces = {"application/json", "application/xml"})
    public Object getCountry(@PathVariable String Title) {
        if (imdb_repository.findImdbByTitle(Title) != null) {
            return imdb_repository.findImdbByTitle(Title);
        } else {
            return new ResponseEntity<Imdb>(HttpStatus.NOT_FOUND);
        }
    }

    @ApiOperation(value = "Delete IMDB movie by title", response = String.class)
    @DeleteMapping(value = "/imdb_movies/delete/{Title}",
            produces = {"application/json", "application/xml"})
    public String deleteCountry(@PathVariable String Title) {
        imdb_repository.deleteImdbByTitle(Title);
        return Title + " is deleted";
    }

    @ApiOperation(value = "Update IMDB movie by title", response = ResponseEntity.class)
    @PutMapping(value = "/imdb_movies/update/{Title}",
            produces = {"application/json", "application/xml"},
            consumes = {"application/json", "application/xml"})
    public ResponseEntity<Imdb> updateCountry(@Validated @RequestBody Imdb imdbDetails, @PathVariable String Title) {

        Optional<Imdb> imdbData = Optional.ofNullable(imdb_repository.findImdbByTitle(Title));
        if (imdbData.isPresent()) {
            Imdb imdb = imdbData.get();
            imdb.setActors(imdbDetails.getActors());
            imdb.setAvg_Vote(imdbDetails.getAvg_Vote());
            imdb.setBudget(imdbDetails.getBudget());
            imdb.setCountry(imdbDetails.getCountry());
            imdb.setDescription(imdbDetails.getDescription());
            imdb.setDirector(imdbDetails.getDirector());
            imdb.setDuration(imdbDetails.getDuration());
            imdb.setGenre(imdbDetails.getGenre());
            imdb.setImdb_title_id(imdbDetails.getImdb_title_id());
            imdb.setLanguage(imdbDetails.getLanguage());
            imdb.setMetascore(imdbDetails.getMetascore());
            imdb.setOriginal_title(imdbDetails.getOriginal_title());
            imdb.setProduction(imdbDetails.getProduction());
            imdb.setReviews_Critics(imdbDetails.getReviews_Users());
            imdb.setReviews_Users(imdbDetails.getReviews_Users());
            imdb.setUsa_Gross_Income(imdbDetails.getUsa_Gross_Income());
            imdb.setVotes(imdbDetails.getVotes());
            imdb.setTitle(imdbDetails.getTitle());
            imdb.setWorldwide_Gross_Income(imdbDetails.getWorldwide_Gross_Income());
            imdb.setYear(imdbDetails.getYear());
            imdb.setWriter(imdbDetails.getWriter());
            imdb.setDate_Published(imdbDetails.getDate_Published());
            imdb.setOriginal_title(imdbDetails.getOriginal_title());
            return new ResponseEntity<>(imdb_repository.save(imdb), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    @ApiOperation(value = "Create IMDB movie", response = ResponseEntity.class)
    @PostMapping(value = "/imdb_movies/create",
            produces = {"application/json", "application/xml"},
            consumes = {"application/json", "application/xml"})
    public ResponseEntity<Imdb> createImdb(@RequestBody Imdb imdb) {
        boolean isImdbFound = false;
        for (Imdb imdbs : getAllImdbs()) {
            if (imdbs.getCountry().contains(imdb.getCountry())) {
                isImdbFound = true;
                break;
            }
        }
        if (!isImdbFound) {
            try {
                Imdb newImdb = imdb_repository
                        .save(imdb);
                return new ResponseEntity<>(newImdb, HttpStatus.CREATED);
            } catch (Exception e) {
                return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
            return new ResponseEntity<>(null, HttpStatus.UNPROCESSABLE_ENTITY);
        }

    }
}
