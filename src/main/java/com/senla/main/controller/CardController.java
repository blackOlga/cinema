package com.senla.main.controller;

import com.senla.main.repository.CardRepositoryImpl;
import com.senla.main.repository.PersonRepositoryImpl;
import com.senla.main.service.CardService;
import com.senla.main.service.CardServiceImpl;
import com.senla.main.service.PersonService;
import com.senla.main.service.PersonServiceImpl;
import lombok.Builder;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Builder
@Data
public class CardController {
    final CardRepositoryImpl cardRepository = new CardRepositoryImpl();
    final CardService cardService = new CardServiceImpl(cardRepository);

    public static void startCard() {

        PersonController personController = PersonController.builder().build();

    }

}
