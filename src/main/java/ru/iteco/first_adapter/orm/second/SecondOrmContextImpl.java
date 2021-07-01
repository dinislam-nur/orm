package ru.iteco.first_adapter.orm.second;

import ru.iteco.first_adapter.Db;
import ru.iteco.first_adapter.DbUserEntity;
import ru.iteco.first_adapter.DbUserInfoEntity;

import java.util.Set;

class SecondOrmContextImpl implements SecondOrmContext {

    private final Db<DbUserEntity> userDb;
    private final Db<DbUserInfoEntity> userInfoDb;

    SecondOrmContextImpl(Db<DbUserEntity> userDb, Db<DbUserInfoEntity> userInfoDb) {
        this.userDb = userDb;
        this.userInfoDb = userInfoDb;
    }

    @Override
    public Set<DbUserEntity> getUsers() {
        return userDb.getAll();
    }

    @Override
    public Set<DbUserInfoEntity> getUserInfos() {
        return userInfoDb.getAll();
    }
}
