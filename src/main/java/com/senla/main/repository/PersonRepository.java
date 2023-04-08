package com.senla.main.repository;

import com.senla.main.model.Person;


import java.util.List;


public interface PersonRepository {

    public boolean create(Person person);

    List<Person> getAllPersons();


    boolean exit(String username, String password);

    Person getPerson(Integer id);

    public boolean updatePersonById(Integer id, Person person);

    Person getByUsername(String username);

    boolean deletePersonById(Integer id);
}

