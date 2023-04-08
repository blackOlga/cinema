package com.senla.main.model;

import lombok.Builder;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
@Data
@Builder
@Slf4j
public class Card {
    private Integer id;
    private String username;
    private String title;
    private Integer place;
    private Integer price;


}
