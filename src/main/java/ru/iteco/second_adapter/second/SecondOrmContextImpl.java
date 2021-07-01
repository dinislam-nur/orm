package ru.iteco.second_adapter.second;

import ru.iteco.second_adapter.Db;
import ru.iteco.second_adapter.DbUserEntity;
import ru.iteco.second_adapter.DbUserInfoEntity;

import java.util.Set;

class SecondOrmContextImpl implements SecondOrmContext {

    private final Db<DbUserEntity> userDb;
    private final Db<DbUserInfoEntity> userInfoDb;

    public SecondOrmContextImpl(Db<DbUserEntity> userDb, Db<DbUserInfoEntity> userInfoDb) {
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
