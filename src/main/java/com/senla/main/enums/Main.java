package com.senla.main.enums;


import com.senla.main.controller.CardController;
import com.senla.main.controller.MovieController;
import com.senla.main.controller.PersonController;
import com.senla.main.model.Person;
import lombok.Builder;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Builder
@Setter
@Slf4j
public class Main {
    public static void main(String[] args) {
        PersonController personController = PersonController.builder().build();
        personController.getCardRepository();
        personController.getMovieRepository();
        CardController cardController=CardController.builder().build();
        MovieController movieController = MovieController.builder().build();
        personController.startCinema();
        Person person = Person.builder().build();


    }
}