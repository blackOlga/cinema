package com.senla.main.enums;

import com.senla.main.repository.MovieRepository;

public enum Role implements Describable {
    USER("пользователь"),
    ADMIN("администратор"),
    MANAGER("менеджер");


    private final String description;

    Role(String description) {
        this.description = description;
    }
    public String getDescription(){
        return description;
    }

    @Override
    public String describe() {
        return "Описание роли";
    }
}
