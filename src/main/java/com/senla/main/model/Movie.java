package com.senla.main.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
@Builder
@AllArgsConstructor
public class Movie {
    private Integer id;
    private String title;
    private String dateTime;



}
