package co.com.poli.movies.services;

import co.com.poli.movies.entities.Movie;

import java.util.List;

public interface MovieService  {
    List<Movie> findAll();
    void save(Movie movie);
    Movie findById(Long id);
    void delete(Movie movie);




}
