package ru.iteco.first_adapter;

import java.time.LocalDateTime;

/**
 * DbUserInfoEntity.
 *
 * @author Ilya_Sukhachev
 */
public class DbUserInfoEntity implements DbEntity {

    private Long id;
    private String name;
    private LocalDateTime birthday;

    public DbUserInfoEntity() {
    }

    public DbUserInfoEntity(Long id, String name, LocalDateTime birthday) {
        this.id = id;
        this.name = name;
        this.birthday = birthday;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDateTime birthday) {
        this.birthday = birthday;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DbUserInfoEntity that = (DbUserInfoEntity) o;

        if (!id.equals(that.id)) return false;
        if (!name.equals(that.name)) return false;
        return birthday.equals(that.birthday);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + name.hashCode();
        result = 31 * result + birthday.hashCode();
        return result;
    }
}
