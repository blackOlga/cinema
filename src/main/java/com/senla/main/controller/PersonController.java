package com.senla.main.controller;

import com.senla.main.enums.Role;
import com.senla.main.model.Card;
import com.senla.main.model.Movie;
import com.senla.main.model.Person;
import com.senla.main.repository.*;
import com.senla.main.service.*;
import lombok.Builder;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;
import java.util.Scanner;

import static com.senla.main.enums.Role.USER;

@Builder
@Slf4j
@Data
public class PersonController {

    private final PersonRepository personRepository = PersonRepositoryImpl.builder().build();
    final PersonService personService = new PersonServiceImpl(personRepository);
    final MovieServiceImpl movieRepository = MovieServiceImpl.builder().build();


    final CardServiceImpl cardRepository = CardServiceImpl.builder().build();


    public PersonController() {
    }

    public void startCinema() {

        personService.getAllPersons().forEach(System.out::println);


        Movie movie = Movie.builder()
                .dateTime("")
                .title("")
                .build();

        Card card = Card.builder().id(movie.getId())
                .username("username")
                .price(10)
                .build();

        Person person = Person.builder()
                .username("username")
                .password("password")
                .role("role")
                .build();


        Role role = USER;

        switch (role) {
            case USER -> {
                System.out.println("Меню для юзера");
                log.info("Открыто главное меню");
                startMenu();
                log.info("Пользователь открыл меню выбора");
            }
            case ADMIN -> {
                System.out.println("Меню для админа");
                startMenuAdmin();
                log.info("Открыто главное меню для администратора");
            }
            case MANAGER -> {
                System.out.println("Меню для менеджера");
                startMenuManager();
                log.info("открыто главное меню для менеджера");
            }
            default -> System.out.println("Пользователь не авторизован");
        }
    }


    public void startMenu() {

        System.out.println("""
                Добро пожаловать!!!
                1-зарегистрироваться
                2- войти
                0- выйти
                Пожалуйста, сделайте Ваш выбор , введите цифру :
                """);

        try (Scanner scanner = new Scanner(System.in)) {

            String step = scanner.nextLine();
            switch (step) {
                case "1" -> {
                    System.out.println("Меню регистрации");

                    try {
                        Person person = Person.builder().username("username").password("password").build();
                        System.out.print("Введите логин : ");
                        String username = scanner.nextLine();
                        System.out.print("Введите пароль : ");
                        String password = scanner.nextLine();


                        person.setUsername(username);
                        person.setPassword(password);
                        boolean b = personService.create();
                        System.out.println("Регистрация прошла успешна !");

                        acquireUser();

                    } catch (NoSuchElementException e) {
                        e.printStackTrace();
                    }
                }
                case "2" -> {
                    System.out.println("Меню входа");
                    try {
                        Person person = Person.builder().build();
                        System.out.print("Введите логин : ");
                        String username = scanner.nextLine();
                        System.out.print("Введите пароль : ");
                        String password = scanner.nextLine();
                        boolean exit = personService.exit(username, password);
                        if (exit) {
                            System.out.println("Вы вошли в основное меню");
                            acquireUser();
                        } else {
                            System.out.println("Пользователь не существует");
                        }
                    } catch (
                            RuntimeException e) {
                        System.out.println("Логин или пароль ввели неверно. Попробуйте ещё раз");
                    }

                }
                case "0" -> {
                    System.out.println("назад");
                    startMenu();
                }
                default -> {
                    System.out.println("Вы ввели некорректные данные");
                    startMenu();
                }
            }
        } catch (NoSuchElementException e) {
            e.printStackTrace();
        }
    }


    public void startMenuAdmin() {
        System.out.println("""
                Добро пожаловать!!!
                1-Введите логин и пароль
                Пожалуйста, сделайте Ваш выбор , введите цифру :
                """);

        try (
                Scanner scanner = new Scanner(System.in)) {

            String step = scanner.nextLine();
            switch (step) {
                case "1" -> {
                    System.out.println("Меню входа");
                    try {
                        System.out.print("Введите логин : ");
                        String username = scanner.nextLine();
                        System.out.print("Введите пароль : ");
                        String password = scanner.nextLine();
                        boolean exit = personService.exit(username, password);
                        if (exit) {
                            System.out.println("Вы вошли в основное меню");
                            acquireAdmin();
                        } else {
                            System.out.println("Пользователь не существует");
                        }
                    } catch (
                            RuntimeException e) {
                        System.out.println("Логин или пароль ввели неверно. Попробуйте ещё раз");

                    }
                }
            }
        }
    }

    private void startMenuManager() {
        System.out.println("""
                Добро пожаловать!!!
                1-Введите логин и пароль
                 Пожалуйста, сделайте Ваш выбор , введите цифру :
                """);

        try (Scanner scanner = new Scanner(System.in)) {

            String step = scanner.nextLine();
            switch (step) {
                case "1" -> {
                    System.out.println("Меню входа");
                    try {
                        System.out.print("Введите логин : ");
                        String username = scanner.nextLine();
                        System.out.print("Введите пароль : ");
                        String password = scanner.nextLine();
                        boolean exit = personService.exit(username, password);
                        if (exit) {
                            System.out.println("Вы вошли в основное меню");
                            acquireManager();
                        } else {
                            System.out.println("Пользователь не существует");
                        }
                    } catch (
                            RuntimeException e) {
                        System.out.println("Логин или пароль ввели неверно. Попробуйте ещё раз");
                    }
                }
                case "0" -> startMenu();
            }
        } catch (NoSuchElementException e) {
            e.printStackTrace();
        }
    }

    public boolean acquireUser() {
        System.out.println("""
                1-посмотреть все  фильмы
                2-купить билет
                3-вернуть билет
                4- посмотреть свои билеты
                5- вернуться в главное меню
                Пожалуйста, сделайте Ваш выбор , введите цифру :
                """);
        try (Scanner scanner = new Scanner(System.in)) {
            String step = scanner.nextLine();
            switch (step) {
                case "1" -> {
                    System.out.println("Меню фильмов");
                    log.info("Открыто меню фильмов");
                    movieRepository.getAllMovies();

                }
                case "2" -> {
                    System.out.println("Купить билет");
                    try  {
                        System.out.print("Введите  имя пользователя: ");
                       String username = scanner.nextLine();
                        System.out.print("Введите номер билета , который хотите купить : ");
                      Integer  id = scanner.nextInt();
                    log.info("Пользователь  покупает билет ");
                    cardRepository.buyCard(username, id);
                } catch (Exception e) {
                    System.out.println("Вы ввели некоректные данные. Проверьте имя и номер билета");
                }
                return false;
                }

                case "3" -> {
                    System.out.println("Вернуть билет");
                    try{
                        System.out.print("Для возврата купленного билета введите id билета : ");
                      Integer  id = scanner.nextInt();
                    log.info("Пользователь вернул билет");
                    cardRepository.replaceCard(id);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    return false;

                }
                case "4" -> {
                    System.out.println("Посмотреть купленные билеты");
                    try {
                        System.out.print("Введите  id билета для просмотра : ");
                     String   username= scanner.nextLine();
                    log.info("Пользователь   посмотрел купленные билеты");
                    cardRepository.getByCard(username);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    return false;
                }
                case "5" -> startMenu();
                default -> System.out.println(" Вы ввели некорректные данные");
            }
        } catch (NoSuchElementException e) {
            System.out.println(" Вы ввели не корректные данные, попробуйте ещё раз");
        }
        return false;
    }


    private void acquireManager() {
        System.out.println("""
                1-посмотреть все  фильмы
                2-купить билет
                3-вернуть билет
                4- посмотреть  билеты  всех пользователей
                5- обновить фильм
                6- вернуться в главное меню
                Пожалуйста, сделайте Ваш выбор , введите цифру :
                """);
        try (Scanner scanner = new Scanner(System.in)) {
            String step = scanner.nextLine();
            switch (step) {
                case "1" -> {
                    System.out.println("Меню фильмов");
                    log.info("Открыто меню фильмов");
                    movieRepository.getAllMovies();
                }
                case "2" -> {
                    System.out.println("Купить билет");
                    log.info("Менеджер  покупает билет ");
                    cardRepository.buyCard("username", 1);
                }
                case "3" -> {
                    System.out.println("Вернуть билет");
                    log.info("Менеджер возвращает билет");
                    cardRepository.replaceCard(0);
                }
                case "4" -> {
                    System.out.println(" Просмотр  купленных  билетов");
                    log.info("Менеджер смотрит купленные  пользователями билеты");
                    cardRepository.getByCard("");
                }
                case "5" -> {
                    System.out.println("Обновить фильм");
                    log.info("Обновлен фильм " + LocalDateTime.now());
                    movieRepository.updateMovieById(1, Movie.builder().build());
                }
                case "6" -> startMenu();
                default -> System.out.println(" Вы ввели некорректные данные");
            }
        } catch (NoSuchElementException e) {
            e.printStackTrace();
        }
    }

    private boolean acquireAdmin() {
        System.out.println("""
                1-посмотреть все  фильмы
                2-купить билет
                3-вернуть билет
                4- посмотреть свои билеты
                5-обновить фильм
                6-удалить пользователя
                7- удалить фильм
                8- вернуться в главное меню
                Пожалуйста, сделайте Ваш выбор , введите цифру :
                """);
        try (Scanner scanner = new Scanner(System.in)) {
            var step = scanner.nextLine();
            switch (step) {
                case "1" -> {
                    System.out.println("Меню фильмов");
                    log.info("Открыто меню фильмов");
                    movieRepository.getAllMovies();
                }
                case "2" -> {
                    System.out.println("Купить билет");
                    log.info("Администратор покупает билет ");
                    cardRepository.buyCard("username", 1);
                }
                case "3" -> {
                    System.out.println("Вернуть билет");
                    log.info("Пользователь возвращает билет");
                    cardRepository.replaceCard(0);
                }
                case "4" -> {
                    System.out.println("Посмотреть купленные билеты");
                    log.info("Администратор открыл просмотр купленных пользователем билетов");
                    cardRepository.getByCard("");
                }
                case "5" -> {
                    System.out.println("Обновить фильм");
                    log.info("Администратор обновил фильм");
                    movieRepository.updateMovieById(1, Movie.builder().build());
                }
                case "6" -> {
                    System.out.println("Меню для удаления пользователя");
                    try  {
                        System.out.print("Введите  id пользователя для обновления данных пользователя : ");
                        Integer id = scanner.nextInt();
                        log.info("Администратор удалил пользователя за нарушение правил  пользования контентом");
                        personService.deletePersonById(id);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                case "7" -> {
                    System.out.println("Меню удаления фильма");
                    log.info("Администратор удалил фильм" + LocalDateTime.now());
                    try  {
                        Movie movie = Movie.builder().build();
                        System.out.print("Введите  id фильма для удаления  : ");
                        Integer id = scanner.nextInt();

                        movieRepository.deleteByIdMovie(id);

                    } catch (NoSuchElementException e) {
                        e.printStackTrace();
                    }
                }

                case "8" -> startMenu();

                default -> System.out.println(" Вы ввели некорректные данные");


            }
        }
        return false;
    }
}
