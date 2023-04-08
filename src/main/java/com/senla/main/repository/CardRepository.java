package com.senla.main.repository;

import com.senla.main.model.Card;

public interface CardRepository {
    int[][] getAllCards();

    boolean create(Card card);

    Card getByCard(String username);


    boolean buyCard(String username, Integer id);

    boolean replaceCard(Integer id);

    boolean deleteByIdCard(Integer id);

}