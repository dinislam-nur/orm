package ru.iteco.first_adapter.adapter;

import ru.iteco.first_adapter.user.User;
import ru.iteco.first_adapter.user.UserInfo;

import java.util.Set;

public interface UserRepository {

    void save(User user);

    User get(Long id);

    User getByLogin(String login);

    Set<User> getByName(String name);

    UserInfo getUserInfo(Long id);

    void update(User user);

    void remove(User user);
}
