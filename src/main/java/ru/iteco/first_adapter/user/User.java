package ru.iteco.first_adapter.user;

public class User {

    private final Long id;
    private final String login;
    private final String password;
    private final UserInfo userInfo;

    public User(Long id, String login, String password, UserInfo userInfo) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.userInfo = userInfo;
    }

    public Long getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }
}
