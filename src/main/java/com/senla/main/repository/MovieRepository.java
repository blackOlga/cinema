package com.senla.main.repository;

import com.senla.main.model.Movie;

import java.sql.SQLException;
import java.util.List;

public interface MovieRepository {

    default boolean create(Movie movie) {
        return false;
    }

    List<Movie> getAllMovies();

    Movie getMovie(Integer id);

    Movie update(Movie movie, Integer id);

    Movie getByTitle(String title);

    boolean deleteMovieById(Integer id);
}
