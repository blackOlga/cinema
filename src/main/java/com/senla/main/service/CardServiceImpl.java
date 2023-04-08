package com.senla.main.service;

import com.senla.main.model.Card;
import com.senla.main.repository.CardRepository;
import lombok.Builder;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;
import java.util.Scanner;

@Slf4j
@Builder

public class CardServiceImpl implements CardService {

    private final CardRepository cardRepository;

    public CardServiceImpl(CardRepository cardRepository) {
        this.cardRepository = cardRepository;

    }

    @Override
    public boolean createCard() {
        try (Scanner scanner = new Scanner(System.in)) {
            Card card = Card.builder().username("username").title("title").place(1).price(10).build();
            System.out.print("Введите название фильма : ");
            String title = scanner.nextLine();
            System.out.print("Введите место : ");
            Integer place = scanner.nextInt();
            System.out.print("Введите цену билета: ");
            Integer price = scanner.nextInt();

            card.setTitle(title);
            card.setPlace(place);
            card.setPrice(price);


            log.info("Билет с  " + title + " " + place + " " + price + " " + " создан " + LocalDateTime.now());
            return cardRepository.create(card);

        } catch (NoSuchElementException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean deleteByIdCard(Integer id) {
        try (Scanner scanner = new Scanner(System.in)) {
            Card card = Card.builder().id(id).build();
            System.out.print("Введите  id билета для удаления  : ");
            id = scanner.nextInt();

            log.info("Удалили  " + card + " " + LocalDateTime.now());
            return cardRepository.deleteByIdCard(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Card getByCard(String username) {

            log.info("Просмотр " + " " + LocalDateTime.now());
            return cardRepository.getByCard(username);
        }

    @Override
    public int[][] getAllCards() {
        cardRepository.getAllCards();
        return new int[0][];
    }

    @Override
    public boolean replaceCard(Integer id) {

            log.info("Пользователь вернул билет  " +  id + " " + LocalDateTime.now());

            return cardRepository.replaceCard(id);

    }


    @Override
    public boolean buyCard(String username, Integer id) {

            log.info("Пользователь " + username + "купил билет " + id + " " + LocalDateTime.now());
            return cardRepository.buyCard(username, id);

    }
}

