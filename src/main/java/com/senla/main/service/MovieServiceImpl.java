package com.senla.main.service;

import com.senla.main.model.Movie;
import com.senla.main.repository.MovieRepository;
import lombok.Builder;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

@Data
@Slf4j
@Builder
public class MovieServiceImpl implements MovieService {
    private final MovieRepository movieRepository;

    public MovieServiceImpl(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;

    }

    @Override
    public boolean create() {
        try (Scanner scanner = new Scanner(System.in)) {
            Movie movie = Movie.builder().title("title").dateTime("dateTime").build();
            System.out.print("Введите название фильма ");
            String title = scanner.nextLine();
            System.out.print(" Введите дату и время показа фильма ");
            String dateTime = scanner.nextLine();

            movie.setTitle(title);
            movie.setDateTime(dateTime);

            log.info(" Фильм  " + title + " " + dateTime + " создан " + LocalDateTime.now());
            return movieRepository.create(movie);

        } catch (NoSuchElementException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean deleteByIdMovie(Integer id) {

        log.info("Удалили фильм  " + " " + LocalDateTime.now());
        return movieRepository.deleteMovieById(id);
    }

    @Override
    public List<Movie> getAllMovies() {
        List<Movie> movies = movieRepository.getAllMovies();
        for (Movie value : movies) {
            System.out.println(value.getTitle() + " " + value.getDateTime());
        }
            return getAllMovies();
    }

    @Override
    public void updateMovieById(int i, Movie build) {

    }

}

