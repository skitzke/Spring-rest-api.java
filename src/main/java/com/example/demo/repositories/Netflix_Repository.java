package com.example.demo.repositories;

import com.example.demo.model.Netflix;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface Netflix_Repository extends MongoRepository<Netflix, String> {
    @Query("{ 'Title' : ?0 }")
    Netflix findNetflixByTitle(String Title);

    @Query("{'Title' : ?0 }")
    Netflix deleteNetflixByTitle(String Title);
}
