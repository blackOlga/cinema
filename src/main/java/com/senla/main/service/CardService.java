package com.senla.main.service;

import com.senla.main.model.Card;

/****
 * Сервис для работы с билетами
 */
public interface CardService {

    /**
     * Сохранение билета в базу данных
     */
    boolean createCard();

    /**
     * Удаление билета из базы данных
     */
    boolean deleteByIdCard(Integer id);

    /**
     * Просмотр купленных пользователем билетов
     */
    Card getByCard(String username);

    /**
     * Получение всех билетов
     */
    public int[][] getAllCards();

    /**
     * Вернуть билет
     */
    boolean replaceCard(Integer id);

    /**
     * Купить билет
     */
    boolean buyCard(String username,Integer id);

}