package com.senla.main.repository;

import com.senla.main.model.Card;
import com.senla.main.util.ConnectionManager;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Slf4j
@Data

public class CardRepositoryImpl implements CardRepository {
    public int[][] getAllCards() {
        int[][] arraysCard = new int[6][6];
        try (Connection connection = ConnectionManager.open()) {
            PreparedStatement statement = connection.prepareStatement("SELECT *FROM card WHERE username IS NULL");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Integer id = resultSet.getInt("id");
                String username = resultSet.getString("username");
                String title = resultSet.getString("title");
                Integer place = resultSet.getInt("place");
                Integer price = resultSet.getInt("price");
                Card card = Card.builder().id(id).username(username).title(title).place(place).price(price).build();
                return arraysCard;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public boolean create(Card card) {
        try (Connection connection = ConnectionManager.open()) {
            PreparedStatement statement = connection.prepareStatement
                    ("INSERT INTO card(username,title,place,price) VALUES (?,?,?,?)");
            statement.setString(1, card.getUsername());
            statement.setString(2, card.getTitle());
            statement.setInt(3, card.getPlace());
            statement.setInt(4, card.getPrice());
            statement.execute();
            return true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Card getByCard(String username) {
        try (Connection connection = ConnectionManager.open()) {
            PreparedStatement statement = connection.prepareStatement
                    ("SELECT *FROM card WHERE username=?");
            statement.setString(1, username);
            statement.execute();
            return Card.builder().username(username).build();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean buyCard(String username, Integer id) {
        try (Connection connection = ConnectionManager.open()) {
            PreparedStatement statement = connection.prepareStatement
                    ("UPDATE `card` SET `username`= VALUES (?) WHERE card.id = ?");
            statement.setString(1, username);
            statement.setInt(2, id);
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean replaceCard(Integer id) {
        try (Connection connection = ConnectionManager.open()) {
            PreparedStatement statement = connection.prepareStatement
                    ("UPDATE card SET username=''  WHERE id=?");
            statement.setInt(1, id);
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean deleteByIdCard(Integer id) {
        try (Connection connection = ConnectionManager.open()) {
            PreparedStatement statement = connection.prepareStatement("DELETE FROM card WHERE id = ?");
            statement.setInt(1, id);
            return statement.execute();
        } catch (SQLException e) {
            log.info("При удалении билета по id произошла ошибка");
            throw new RuntimeException();
        }
    }
}