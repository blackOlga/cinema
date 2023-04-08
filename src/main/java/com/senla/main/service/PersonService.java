package com.senla.main.service;

import com.senla.main.model.Person;

import java.util.List;

/**
 * Сервис для работы с пользователем
 */
public interface PersonService {
    /**
     * Сохранение пользователя в базу данных
     */
   boolean create(Person person);

    /**
     * Удаление пользователя из базы данных
     */
    boolean deletePersonById(Integer id);

    /**
     * Получение пользователя по логину
     */
    Person getByUsername(String username);

    /**
     * Получение всех пользователей
     */
    List<Person> getAllPersons();

    /**
     * Обновление пользователя по id
     */
    boolean updatePersonById(Integer id, Person person);

    /**
     * Вход зарегистрированного пользователя
     */

    boolean exit(String username, String password);


}