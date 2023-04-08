package com.senla.main.service;

import com.senla.main.model.Movie;

import java.util.List;

/***
 * Сервис для работы с фильмом
 */
public interface MovieService {


        /**
         * Сохранение фильма в базу данных
         */
        boolean create();

        /**
         * Удаление фильма из базы данных
         */
        boolean deleteByIdMovie(Integer id);

        /**
         * Получение всех фильмов
         */
        List<Movie> getAllMovies();

        /**
         *Обновление фильма по id
         */
        void updateMovieById(int i, Movie build);
}


