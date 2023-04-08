package com.senla.main.service;

import com.senla.main.model.Person;
import com.senla.main.repository.PersonRepository;
import lombok.Builder;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

@Slf4j
@Builder
@Data
public class PersonServiceImpl implements PersonService {
    private final PersonRepository personRepository;

    public PersonServiceImpl(PersonRepository personRepository) {

        this.personRepository = personRepository;
    }

    @Override
    public boolean create(Person person) {
        log.info("Пользователь " +  " зарегистрировался " + LocalDateTime.now());
        return personRepository.create( person);
    }

    @Override
    public boolean deletePersonById(Integer id) {

            log.info("Удалили пользователя по id  "  +" " + LocalDateTime.now());
            return personRepository.deletePersonById(id);
        }


    @Override
    public Person getByUsername(String username) {
        try (Scanner scanner = new Scanner(System.in)) {
            Person person = Person.builder().username("username").build();
            System.out.print("Введите  имя пользователя для поиска : ");
            username = scanner.nextLine();

            log.info("Получили " + username + LocalDateTime.now());

            return personRepository.getByUsername(username);
        }
    }

    @Override
    public List<Person> getAllPersons() {

        return personRepository.getAllPersons();

    }

    @Override
    public boolean updatePersonById(Integer id, Person person) {

            log.info("Обновили " + person + LocalDateTime.now());
            return personRepository.updatePersonById(id, person);

        }


    @Override
    public boolean exit(String username, String password) {

        log.info("Пользователь " + username + " " + " вошёл в программу " + " " + LocalDateTime.now());
        return personRepository.exit(username, password);
    }
}
