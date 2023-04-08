package com.senla.main.controller;

import com.senla.main.repository.MovieRepositoryImpl;

import com.senla.main.service.MovieService;
import com.senla.main.service.MovieServiceImpl;
import lombok.Builder;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
@Builder
public class MovieController {

    final MovieRepositoryImpl movieRepository = MovieRepositoryImpl.builder().build();
    final MovieService movieService = new MovieServiceImpl(movieRepository);

    public void startMovie() {
        PersonController personController = PersonController.builder().build();

        movieService.getAllMovies();
        movieService.create();


    }

}
