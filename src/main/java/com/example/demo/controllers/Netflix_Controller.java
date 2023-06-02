package com.example.demo.controllers;

import com.example.demo.model.Netflix;
import com.example.demo.repositories.Netflix_Repository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Api(tags = "Netflix")
@RestController
public class Netflix_Controller {

    public final Netflix_Repository netflix_repository;

    public Netflix_Controller(Netflix_Repository netflix_repository) {
        this.netflix_repository = netflix_repository;
    }

    @ApiOperation(value = "Get list of Netflix movies", response = List.class)
    @GetMapping(path = "/netflix",
            produces = {"application/json", "application/xml"})
    public List<Netflix> getAllNetflixs() {
        return netflix_repository.findAll();
    }

    @ApiOperation(value = "Get one Netflix movie by title", response = Netflix.class)
    @GetMapping(value = "/netflix/{Title}",
            produces = {"application/json", "application/xml"})
    public Object getNetflixMovie(@PathVariable String Title) {
        if (netflix_repository.findNetflixByTitle(Title) != null) {
            return netflix_repository.findNetflixByTitle(Title);
        } else {
            return new ResponseEntity<Netflix>(HttpStatus.NOT_FOUND);
        }
    }

    @ApiOperation(value = "Delete Netflix movie by title", response = String.class)
    @DeleteMapping(value = "/netflix/delete/{Title}",
            produces = {"application/json", "application/xml"})
    public String deleteNetflixMovie(@PathVariable String Title) {
        netflix_repository.deleteNetflixByTitle(Title);
        return Title + " is deleted";
    }

    @ApiOperation(value = "Update Netflix movie by title", response = ResponseEntity.class)
    @PutMapping(value = "/netflix/update/{Title}",
            produces = {"application/json", "application/xml"},
            consumes = {"application/json", "application/xml"})
    public ResponseEntity<Netflix> updateNetflixMovie(@Validated @RequestBody Netflix netflixDetails, @PathVariable String Title) {
        Optional<Netflix> netflixData = Optional.ofNullable(netflix_repository.findNetflixByTitle(Title));
        if (netflixData.isPresent()) {
            Netflix netflix = netflixData.get();
            netflix.setCast(netflixDetails.getCast());
            netflix.setCountry(netflixDetails.getCountry());
            netflix.setDate_added(netflixDetails.getDate_added());
            netflix.setDescription(netflixDetails.getDescription());
            netflix.setDirector(netflixDetails.getDirector());
            netflix.setListed_in(netflixDetails.getListed_in());
            netflix.setRating(netflixDetails.getRating());
            netflix.setDuration(netflixDetails.getDuration());
            netflix.setRelease_year(netflixDetails.getRelease_year());
            netflix.setShow_id(netflixDetails.getShow_id());
            netflix.setTitle(netflixDetails.getTitle());
            netflix.setType(netflixDetails.getType());
            return new ResponseEntity<>(netflix_repository.save(netflix), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    @ApiOperation(value = "Create Netflix movie", response = ResponseEntity.class)
    @PostMapping(value = "/netflix/create",
            produces = {"application/json", "application/xml"},
            consumes = {"application/json", "application/xml"})
    public ResponseEntity<Netflix> createNetflixMovie(@RequestBody Netflix netflix) {
        boolean isTitleFound = false;
        for (Netflix netflixes : getAllNetflixs()) {
            if (netflixes.getTitle().contains(netflix.getTitle())) {
                isTitleFound = true;
                break;
            }
        }
        if (!isTitleFound) {
            try {
                Netflix newNetflix = netflix_repository
                        .save(netflix);
                return new ResponseEntity<>(newNetflix, HttpStatus.CREATED);
            } catch (Exception e) {
                return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
            return new ResponseEntity<>(null, HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }
}
