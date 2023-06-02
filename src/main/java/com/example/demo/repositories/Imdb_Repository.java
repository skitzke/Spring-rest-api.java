package com.example.demo.repositories;

import com.example.demo.model.Imdb;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface Imdb_Repository extends MongoRepository<Imdb, String> {
    @Query("{ 'Title' : ?0 }")
    Imdb findImdbByTitle(String Title);

    @Query("{'Title' : ?0 }")
    Imdb deleteImdbByTitle(String Title);
}
