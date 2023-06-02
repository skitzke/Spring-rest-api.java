package com.example.demo.consumer;


import com.example.demo.exceptions.ReportYearNotAvailable;
import org.json.JSONObject;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * This class consumes the API
 */
public class RestClient {
    static final String GET_NETFLIX = "http://localhost:8080/netflix/";
    RestTemplate restTemplate;
    HttpHeaders headers;
    HttpEntity<String> entity;

    public RestClient() {
        this.restTemplate = new RestTemplate();
        this.headers = new HttpHeaders();
        this.entity = new HttpEntity<>("body", headers);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
    }

    /**
     * Gets average vote of IMDB movie title
     * @param title Name of movie
     * @return Average vote
     */
    public double getIMDBMovieAvgVote(String title)
    {
        final String GET_IMDB_MOVIE = "http://localhost:8080/imdb_movies/" + title;

        ResponseEntity<String> responseEntity = restTemplate.exchange(GET_IMDB_MOVIE, HttpMethod.GET, entity, String.class);
        String object = responseEntity.getBody();
        JSONObject obj = new JSONObject(object);
        return obj.getDouble("avg_Vote");
    }

    /**
     * Gets name of country where Netflix movie is released. If Movie is released in two or more countries, it gets only the first one
     * @param title Name of movie
     * @return The country where movie is first released
     */
    public String getNetflixMovieCountry(String title)
    {
        ResponseEntity<String> responseEntity = restTemplate.exchange(GET_NETFLIX + title, HttpMethod.GET, entity, String.class);
        String object = responseEntity.getBody();
        JSONObject obj = new JSONObject(object);
        String country = obj.getString("country");
        String []countryArray = country.split(",");
        return countryArray[0];
    }

    /**
     * Gets Netflix movie year and country of release based on a existing movie title
     * @param title Existing Netflix movie title
     * @return Hashmap with release year as a key and country as a value
     */
    public HashMap<Integer, String> getNetflixMovieYearAndCountryOfRelease(String title)
    {
        ResponseEntity<String> responseEntity = restTemplate.exchange(GET_NETFLIX + title, HttpMethod.GET, entity, String.class);
        String object = responseEntity.getBody();
        JSONObject obj = new JSONObject(object);
        int avgVote = obj.getInt("release_year");
        String country = obj.getString("country");
        String []countryArray = country.split(",");
        HashMap<Integer, String> hmap = new HashMap<>();
        hmap.put(avgVote, countryArray[0]);
        return hmap;
    }

    /**
     * Gets country happiness report based on a country name and year of report
     * @param country Country name
     * @param reportYear Year desired for the report
     * @return Country happiness rank
     */
    public double getCountryHappinessReport(String country, int reportYear)
    {
        final String GET_COUNTRY_HAPPINESS = "http://localhost:8080/"+ reportYear +"_report/" + country;

        ResponseEntity<String> responseEntity = restTemplate.exchange(GET_COUNTRY_HAPPINESS, HttpMethod.GET, entity, String.class);
        String object = responseEntity.getBody();
        JSONObject obj = new JSONObject(object);
        return obj.getInt("happiness_Rank");
    }

    /**
     * Gets the Netflix movie average vote on IMDB and happiness report rank based on the year and country of release
     * @param title The Netflix title of a movie or series
     * @return The average vote on IMDB, Happiness report rank, Happiness report year
     * @throws ReportYearNotAvailable Report with no matching year
     */
    public double[] showHappinessReportAndImdbRating(String title) throws ReportYearNotAvailable {
        HashMap<Integer, String> netflixYearOfRelease = getNetflixMovieYearAndCountryOfRelease(title);
        int reportYear = 0;
        String country = null;

        Set set = netflixYearOfRelease.entrySet();
        for (Object o : set) {
            Map.Entry mEntry = (Map.Entry) o;
            reportYear = (int) mEntry.getKey();
            country = mEntry.getValue().toString();
        }
        if(reportYear >= 2015 && reportYear <= 2019) {
            double imdbAvgVote = getIMDBMovieAvgVote(title);
            double happinessReportRank = getCountryHappinessReport(country, reportYear);
            return new double[]{imdbAvgVote, happinessReportRank, reportYear};
        }
        else
        {
            throw new ReportYearNotAvailable();
        }
    }
}
