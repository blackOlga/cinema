package com.senla.main.repository;

import com.senla.main.controller.PersonController;
import com.senla.main.model.Person;
import com.senla.main.util.ConnectionManager;
import lombok.Builder;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Builder
@Slf4j
@Data
public class PersonRepositoryImpl implements PersonRepository {

    private PersonController personController;

    public PersonRepositoryImpl(PersonController personController) {
        this.personController = personController;
    }

    @Override
    public boolean create(Person person) {

        try (Connection connection = ConnectionManager.open()) {
            PreparedStatement statement =
                    connection.prepareStatement
                            ("INSERT INTO person(username,password)VALUES(?,?)");
            statement.setString(1, person.getUsername());
            statement.setString(2, person.getPassword());
            statement.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<Person> getAllPersons() {
        List<Person> persons = new ArrayList<>();
        try (Connection connection = ConnectionManager.open()) {
            PreparedStatement statement = connection.prepareStatement("SELECT *FROM person");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Integer id = resultSet.getInt("id");
                String username = resultSet.getString("username");
                String password = resultSet.getString("password");
                String role = resultSet.getString("role");
                Person person = Person.builder().id(id).username(username).password(password).role(role).build();
                persons.add(person);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return persons;
    }


    @Override
    public boolean exit(String username, String password) {

        try (Connection connection = ConnectionManager.open()) {
            PreparedStatement statement = connection.prepareStatement
                    ("SELECT * FROM person  WHERE username=? AND  password=?");
            statement.setString(1, username);
            statement.setString(2, password);
            if (username != null && password != null) {
                return statement.execute();
                //  username = resultSet.getString("username");
                //  password = resultSet.getString("password");
               // return exit(username, password);




            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }


    @Override
    public Person getPerson(Integer id) {
        PreparedStatement statement;
        try (Connection connection = ConnectionManager.open()) {
            statement = connection.prepareStatement("SELECT* FROM person WHERE id=?");
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String username = resultSet.getString("username");
                String password = resultSet.getString("password");

                return Person.builder().username(username).password(password).build();
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return null;
    }

    @Override
    public boolean updatePersonById(Integer id, Person person) {
        PreparedStatement statement;
        try (Connection connection = ConnectionManager.open()) {
            statement = connection.prepareStatement(
                    "UPDATE `person` SET `password` = (?) WHERE `person`.`id` = ?");
            statement.setString(1, person.getPassword());
            statement.setInt(2, id);
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public Person getByUsername(String username) {
        PreparedStatement statement;
        try (Connection connection = ConnectionManager.open()) {
            statement = connection.prepareStatement(
                    "SELECT id,password FROM person WHERE username = ?");
            statement.setString(1, username);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Integer id = resultSet.getInt("id");
                String password = resultSet.getString("password");
                return Person.builder().id(id).password(password).build();
            }
        } catch (SQLException e) {
            log.info("При получении пользователя " + username + " из базы по имени , произошла ошибка");
            throw new RuntimeException(e);
        }
        if (statement.equals(username)) {
            System.out.println("true");
        } else
            System.out.println("false");
        return null;
    }

    @Override
    public boolean deletePersonById(Integer id) {
        PreparedStatement statement;
        try (Connection connection = ConnectionManager.open()) {
            statement = connection.prepareStatement(
                    "DELETE FROM person WHERE id=?");
            statement.setInt(1, id);
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            log.info("При удалении пользователя по id произошла ошибка");
            throw new RuntimeException();
        }

    }
}
