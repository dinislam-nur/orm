package ru.iteco.first_adapter;

/**
 * DbUserEntity.
 *
 * @author Ilya_Sukhachev
 */
public class DbUserEntity implements DbEntity {

    private Long id;
    private String login;
    private String password;
    private Long userInfoId;

    public DbUserEntity() {
    }

    public DbUserEntity(Long id, String login, String password, Long userInfoId) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.userInfoId = userInfoId;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getUserInfoId() {
        return userInfoId;
    }

    public void setUserInfoId(Long userInfoId) {
        this.userInfoId = userInfoId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DbUserEntity that = (DbUserEntity) o;

        if (!id.equals(that.id)) return false;
        if (!login.equals(that.login)) return false;
        if (!password.equals(that.password)) return false;
        return userInfoId.equals(that.userInfoId);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + login.hashCode();
        result = 31 * result + password.hashCode();
        result = 31 * result + userInfoId.hashCode();
        return result;
    }
}
