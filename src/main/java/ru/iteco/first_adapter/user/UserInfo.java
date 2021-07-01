package ru.iteco.first_adapter.user;

import java.time.LocalDateTime;

public class UserInfo {

    private final Long id;
    private final String name;
    private final LocalDateTime birthDay;

    public UserInfo(Long id, String name, LocalDateTime birthDay) {
        this.id = id;
        this.name = name;
        this.birthDay = birthDay;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public LocalDateTime getBirthDay() {
        return birthDay;
    }
}
