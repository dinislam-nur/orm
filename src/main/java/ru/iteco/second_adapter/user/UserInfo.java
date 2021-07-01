package ru.iteco.second_adapter.user;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserInfo userInfo = (UserInfo) o;

        if (!id.equals(userInfo.id)) return false;
        if (!name.equals(userInfo.name)) return false;
        return birthDay.equals(userInfo.birthDay);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + name.hashCode();
        result = 31 * result + birthDay.hashCode();
        return result;
    }
}
