package ru.iteco.second_adapter.user;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (!id.equals(user.id)) return false;
        if (!login.equals(user.login)) return false;
        if (!password.equals(user.password)) return false;
        return userInfo.equals(user.userInfo);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + login.hashCode();
        result = 31 * result + password.hashCode();
        result = 31 * result + userInfo.hashCode();
        return result;
    }
}
