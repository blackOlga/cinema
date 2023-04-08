package com.senla.main.repository;
import com.senla.main.model.Movie;
import com.senla.main.util.ConnectionManager;
import lombok.Builder;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Data
@Builder
public class MovieRepositoryImpl implements MovieRepository {

    @Override
    public boolean create(Movie movie) {

        try (Connection connection = ConnectionManager.open()) {
            PreparedStatement statement =
                    connection.prepareStatement
                            ("INSERT INTO movie(title,dateTime)VALUES(?,?)");
            statement.setString(1, movie.getTitle());
            statement.setString(2, movie.getDateTime());
            statement.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<Movie> getAllMovies() {
        List<Movie> movies = new ArrayList<>();
        try (Connection connection = ConnectionManager.open()) {
            PreparedStatement statement = connection.prepareStatement("SELECT *FROM movie");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Integer id = resultSet.getInt("id");
                String title = resultSet.getString("title");
                String dateTime = resultSet.getString("dateTime");
                Movie movie = Movie.builder().id(id).title(title).dateTime(dateTime).build();
                movies.add(movie);

            }return movies;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public Movie getMovie(Integer id) {
        try (Connection connection = ConnectionManager.open()) {
            PreparedStatement statement = connection.prepareStatement
                    ("SELECT *FROM movie WHERE id = ?");
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public Movie update(Movie movie, Integer id) {
        try (Connection connection = ConnectionManager.open()) {
            PreparedStatement statement = connection.prepareStatement
                    ("UPDATE movie (dateTime) VALUES(?)  WHERE id = 3");
            statement.setString(1, movie.getDateTime());
            statement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public Movie getByTitle(String title) {
        try (Connection connection = ConnectionManager.open()) {
            PreparedStatement statement = connection.prepareStatement("SELECT title FROM movie");
            statement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }


    @Override
    public boolean deleteMovieById(Integer id) {
        try (Connection connection = ConnectionManager.open()) {
            PreparedStatement statement = connection.prepareStatement("DELETE  movie WHERE id = ?");
            statement.setInt(1, 8);
            statement.executeQuery();
            return true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}