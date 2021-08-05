package ru.iteco.true_adapter.second;

import ru.iteco.true_adapter.db.Db;
import ru.iteco.true_adapter.entity.DbUserEntity;

import java.util.Set;

public class UserContext extends SecondOrmContextBase<DbUserEntity> {

    public UserContext(Db<DbUserEntity> db) {
        super(db);
    }

    @Override
    public Set<DbUserEntity> getUsers() {
        return db.getAll();
    }
}
